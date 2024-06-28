package com.example.mawqifi.features.booking.server.impl

import com.example.mawqifi.exception.ParkingNotFoundException
import com.example.mawqifi.exception.ProfileNotFoundException
import com.example.mawqifi.exception.VehicleNotFoundException
import com.example.mawqifi.exception.VehicleProfileDoesNotMatch
import com.example.mawqifi.features.booking.repository.BookingRepository
import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.booking.server.BookingServer
import com.example.mawqifi.features.booking.server.dto.BookingDto
import com.example.mawqifi.features.booking.server.dto.BookingListItemDto
import com.example.mawqifi.features.parking.repository.ParkingRepository
import com.example.mawqifi.features.profile.repository.ProfileRepository
import com.example.mawqifi.features.profile.repository.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookingServerImpl : BookingServer {

    @Autowired
    lateinit var bookingRepository: BookingRepository

    @Autowired
    lateinit var profileRepository: ProfileRepository

    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    @Autowired
    lateinit var parkingRepository: ParkingRepository


    override fun save(bookingDto: BookingDto): BookingEntity {
        val profile = profileRepository.findById(bookingDto.userId.toLong())
        if (profile.isEmpty) {
            throw ProfileNotFoundException()
        }
        val vehicle = vehicleRepository.findById(bookingDto.vehicleId.toLong())
        if (vehicle.isEmpty) {
            throw VehicleNotFoundException()
        }
        if (vehicle.get().profileEntity != profile.get()) {
            throw VehicleProfileDoesNotMatch()
        }
        val parking = parkingRepository.findById(bookingDto.parkingId.toLong())
        if (profile.isEmpty) {
            throw ParkingNotFoundException()
        }
        val entity = BookingEntity(
            vehicleEntity = vehicle.get(),
            parkingEntity = parking.get(),
            profileEntity = profile.get(),
            from = bookingDto.from,
            until = bookingDto.until,
        )
        return bookingRepository.save(entity)
    }

    override fun getBookingList(profileId: Long): List<BookingListItemDto> {
        val profile = profileRepository.findById(profileId)
        if (profile.isEmpty) {
            throw ProfileNotFoundException()
        }
        val list = bookingRepository.findAllByProfileEntityOrderByCreateAtDesc(profile.get()).map {
            if (parkingRepository.findById(it.parkingEntity.id).isEmpty) {
                throw ParkingNotFoundException()
            }
            BookingListItemDto(
                id = it.id,
                imageUrl = it.parkingEntity.bigImageUrl,
                name = it.parkingEntity.name,
                longAddress = it.parkingEntity.longAddress,
                price = it.parkingEntity.price,
                from = it.from,
                until = it.until,
                statusId = it.statusId
            )
        }
        return list
    }

    @Scheduled(fixedRate = 3600000)
    fun chickBookingStatus() {
        val findAll = bookingRepository.findAllByStatusId(BookingEntity.Status.IN_PROGRESS.id)
        findAll.iterator().forEach {
            if (it.until.before(Date(System.currentTimeMillis()))) {
                if (it.statusId == BookingEntity.Status.IN_PROGRESS.id) {
                    bookingRepository.deleteById(it.id)
                    bookingRepository.save(
                        it.copy(
                            statusId = BookingEntity.Status.COMPLETED.id
                        )
                    )
                }
            }
        }
    }
}