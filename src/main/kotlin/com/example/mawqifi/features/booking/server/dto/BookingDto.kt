package com.example.mawqifi.features.booking.server.dto

import java.util.*

data class BookingDto(
    val parkingId: String,
    val userId: String,
    val vehicleId: String,
    val from: Date,
    val until: Date,
)
