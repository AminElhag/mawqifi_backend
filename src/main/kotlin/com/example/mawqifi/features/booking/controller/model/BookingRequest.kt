package com.example.mawqifi.features.booking.controller.model

import com.example.mawqifi.common.Helper
import com.example.mawqifi.features.booking.server.dto.BookingDto
import com.fasterxml.jackson.annotation.JsonProperty

data class BookingRequest(
    @JsonProperty("parking_id") val parkingId: String,
    @JsonProperty("user_id") val userId: String,
    @JsonProperty("vehicle_id") val vehicleId: String,
    @JsonProperty("from") val from: String,
    @JsonProperty("until") val until: String,
) {
    fun toBookingDto()= BookingDto(
        parkingId,userId, vehicleId, Helper.stringToDate(from), Helper.stringToDate(until)
    )
}