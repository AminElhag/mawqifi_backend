package com.example.mawqifi.features.booking.server

import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.booking.server.dto.BookingDetailsDto
import com.example.mawqifi.features.booking.server.dto.BookingDto
import com.example.mawqifi.features.booking.server.dto.BookingListItemDto

interface BookingServer {
    fun save(bookingDto: BookingDto): BookingEntity
    fun getBookingList(profileId: Long): List<BookingListItemDto>
    fun getBookingDriverRequest(typeId: Int, driverId: Long): List<BookingListItemDto>
    fun getBookingDetailsById(bookingId: Long) : BookingDetailsDto
    fun canceledBooking(bookingId: Long)
    fun completedBooking(bookingId: Long)

}
