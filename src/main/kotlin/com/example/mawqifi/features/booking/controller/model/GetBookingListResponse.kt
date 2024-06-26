package com.example.mawqifi.features.booking.controller.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class GetBookingListResponse(
    @JsonProperty("id") val id: Long,
    @JsonProperty("image_url") val imageUrl: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("long_address") val longAddress: String,
    @JsonProperty("price") val price: Double,
    @JsonProperty("from") val from: Date,
    @JsonProperty("until") val until: Date,
    @JsonProperty("status_id") val statusId: Int,
)
