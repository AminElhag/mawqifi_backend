package com.example.mawqifi.features.driver.service.impl

import com.example.mawqifi.exception.DriverNumberIsAlreadyUsedException
import com.example.mawqifi.exception.ProfileNotFoundException
import com.example.mawqifi.features.booking.repository.BookingRepository
import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.booking.server.dto.BookingDto
import com.example.mawqifi.features.booking.server.dto.BookingListItemDto
import com.example.mawqifi.features.driver.repository.DriverRepository
import com.example.mawqifi.features.driver.repository.entity.DriverEntity
import com.example.mawqifi.features.driver.service.DriverService
import com.example.mawqifi.features.driver.service.model.CreateDriverDto
import com.example.mawqifi.features.driver.service.model.DriverDto
import io.jsonwebtoken.io.IOException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DriverServiceImpl : DriverService {

    @Autowired
    lateinit var driverRepository: DriverRepository

    @Autowired
    lateinit var bookingRepository: BookingRepository

    override fun createDriver(createDriverDto: CreateDriverDto): DriverDto {
        val entity = driverRepository.findByPhoneNumber(createDriverDto.phoneNumber)
        if (entity != null) {
            throw DriverNumberIsAlreadyUsedException()
        }
        return driverRepository.save(
            DriverEntity(
                phoneNumber = createDriverDto.phoneNumber,
                fullName = createDriverDto.fullName,
                homeAddress = createDriverDto.homeAddress,
                genderTypeId = createDriverDto.genderTypeId,
                platformType = createDriverDto.platformType,
                createAt = Date(System.currentTimeMillis()),
                updateAt = Date(System.currentTimeMillis()),
                platformDeviceId = createDriverDto.platformDeviceId,
                fcmToken = createDriverDto.fcmToken,
            )
        ).toDriverDto()
    }

    override fun getBookingRequestByType(typeId: Int, userId: Long): List<BookingListItemDto> {
        val parkingEntity = driverRepository.findById(userId).get().parkingEntity ?: return listOf()
        val findAllByDriverIdAndStatusId = if (typeId == BookingEntity.Status.COMPLETED.id) {
            bookingRepository.findAllByDriverIdAndStatusId(userId, typeId)
        } else {
            bookingRepository.findAllByParkingEntityAndStatusIdLessThan(
                statusId = BookingEntity.Status.COMPLETED.id,
                parkingEntity =parkingEntity
            )
        }
        return findAllByDriverIdAndStatusId.map {
            BookingListItemDto(
                id = it.id,
                imageUrl = it.parkingEntity.bigImageUrl,
                name = it.parkingEntity.name,
                longAddress = it.parkingEntity.longAddress,
                price = it.parkingEntity.price,
                from = it.from,
                until = it.until,
                statusId = it.statusId,
                vehicleEntity = it.vehicleEntity
            )
        }
    }

    override fun setFcmToken(fcmToken: String, driverId: Long) {
        val findById = driverRepository.findById(driverId)
        if(findById.isEmpty) throw ProfileNotFoundException()
        driverRepository.save(findById.get().copy(fcmToken = fcmToken))
    }

    override fun bookingAccept(bookingId: Long, driverId: Long) {
        val optional = bookingRepository.findById(bookingId)
        if (optional.isEmpty) throw RuntimeException("Booking not found")
        val driverEntityOptional = driverRepository.findById(driverId)
        if (driverEntityOptional.isEmpty) throw RuntimeException("Driver not found")
        if (optional.get().statusId != BookingEntity.Status.WAITING.id) throw RuntimeException("You can't accept this order")
        bookingRepository.delete(optional.get())
        bookingRepository.save(optional.get().copy(
            driverId = driverEntityOptional.get().id,
            statusId = BookingEntity.Status.IN_PROGRESS.id
        ))
    }

    override fun bookingComplete(bookingId: Long, driverId: Long) {
        val optional = bookingRepository.findById(bookingId)
        if (optional.isEmpty) throw RuntimeException("Booking not found")
        val driverEntityOptional = driverRepository.findById(driverId)
        if (driverEntityOptional.isEmpty) throw RuntimeException("Driver not found")
        if (optional.get().driverId != driverEntityOptional.get().id) throw RuntimeException("This order under another driver")
        if (optional.get().statusId != BookingEntity.Status.IN_PROGRESS.id) throw RuntimeException("You can't complete this order")
        bookingRepository.delete(optional.get())
        bookingRepository.save(optional.get().copy(
            driverId = driverEntityOptional.get().id,
            statusId = BookingEntity.Status.COMPLETED.id
        ))
    }

    override fun getDriverByPhoneNumber(phoneNumber: String): DriverDto? {
        return driverRepository.findByPhoneNumber(phoneNumber)?.toDriverDto()
    }
}