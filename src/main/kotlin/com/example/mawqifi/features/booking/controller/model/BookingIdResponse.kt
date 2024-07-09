package com.example.mawqifi.features.booking.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class BookingIdResponse(
    @JsonProperty("id")
    val id: Long,
)
