package com.example.mawqifi.features.booking.server

import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.booking.server.dto.BookingDto

interface BookingServer {
    fun save(bookingDto: BookingDto): BookingEntity

}
