package com.example.mawqifi.features.booking.server.impl

import com.example.mawqifi.exception.*
import com.example.mawqifi.features.booking.repository.BookingRepository
import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.booking.server.BookingServer
import com.example.mawqifi.features.booking.server.dto.BookingDetailsDto
import com.example.mawqifi.features.booking.server.dto.BookingDto
import com.example.mawqifi.features.booking.server.dto.BookingListItemDto
import com.example.mawqifi.features.driver.repository.DriverRepository
import com.example.mawqifi.features.fcm.FcmService
import com.example.mawqifi.features.fcm.MessageDTO
import com.example.mawqifi.features.parking.repository.ParkingRepository
import com.example.mawqifi.features.profile.repository.ProfileRepository
import com.example.mawqifi.features.profile.repository.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    lateinit var fcmService: FcmService

    @Autowired
    lateinit var driverRepository: DriverRepository


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

        val findAllByParkingEntity = driverRepository.findAllByParkingEntity(parkingEntity = parking.get())
        try {
            findAllByParkingEntity?.forEach { driver ->
                driver?.fcmToken?.let {
                    fcmService.sendNotificationToSpecificDevice(
                        MessageDTO(
                            "New Order Is here", """
                    Mr. ${profile.get().fullName} is here go and take ${vehicle.get().model}
                """.trimIndent(), parking.get().bigImageUrl,
                            mapOf("parking_id" to "${parking.get().id}")
                        ), it
                    )
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return bookingRepository.save(entity)
    }

    override fun getBookingDetailsById(bookingId: Long): BookingDetailsDto {
        val findById = bookingRepository.findById(bookingId)
        if (findById.isEmpty) throw BookingNotFoundException()
        if (findById.get().driverId == null) return findById.get().toBookingDetailsDto()
        val driver = driverRepository.findById(findById.get().driverId!!)
        if (driver.isEmpty) return findById.get().toBookingDetailsDto()
        return findById.get().toBookingDetailsDto(driver.get().toDriverDto())
    }

    override fun canceledBooking(bookingId: Long) {
        val findById = bookingRepository.findById(bookingId)
        if (findById.isEmpty) throw BookingNotFoundException()
        if (findById.get().statusId != BookingEntity.Status.WAITING.id) throw BookingIsNotInWaitingException()
        bookingRepository.save(findById.get().copy(statusId = BookingEntity.Status.CANCELED.id))
    }

    override fun completedBooking(bookingId: Long) {
        val findById = bookingRepository.findById(bookingId)
        if (findById.isEmpty) throw BookingNotFoundException()
        if (findById.get().statusId != BookingEntity.Status.IN_PROGRESS.id) throw BookingIsNotInProgressException()
        bookingRepository.save(findById.get().copy(statusId = BookingEntity.Status.COMPLETED.id))
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

    override fun getBookingDriverRequest(typeId: Int, driverId: Long): List<BookingListItemDto> {
        val list =
            bookingRepository.findAllByDriverIdAndStatusId(driverId = driverId, statusId = typeId).map {
                BookingListItemDto(
                    id = it.id,
                    imageUrl = it.parkingEntity.bigImageUrl,
                    name = it.parkingEntity.name,
                    longAddress = it.parkingEntity.longAddress,
                    price = it.parkingEntity.price,
                    from = it.from,
                    until = it.until,
                    statusId = it.statusId,
                )
            }
        return list
    }
}