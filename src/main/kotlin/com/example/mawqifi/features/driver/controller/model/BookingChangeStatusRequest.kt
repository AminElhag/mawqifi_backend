package com.example.mawqifi.features.driver.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class BookingChangeStatusRequest(
    @JsonProperty("user_id")
    val driverId: Long,
    @JsonProperty("booking_id")
    val bookingId: Long
)
