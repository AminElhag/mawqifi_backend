package com.example.mawqifi.features.driver.service

import com.example.mawqifi.features.booking.server.dto.BookingDto
import com.example.mawqifi.features.booking.server.dto.BookingListItemDto
import com.example.mawqifi.features.driver.service.model.CreateDriverDto
import com.example.mawqifi.features.driver.service.model.DriverDto

interface DriverService {
    fun getDriverByPhoneNumber(phoneNumber: String): DriverDto?
    fun createDriver(createDriverDto: CreateDriverDto): DriverDto
    fun getBookingRequestByType(typeId: Int, userId: Long): List<BookingListItemDto>
    fun bookingAccept(bookingId: Long, driverId: Long)
    fun bookingComplete(bookingId: Long, driverId: Long)
    fun setFcmToken(fcmToken: String, driverId: Long)

}
