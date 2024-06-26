package com.example.mawqifi.features.parking.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GetParkingRequest(
    @JsonProperty("parking_id")
    var parkingId: Long = 0,
    @JsonProperty("name")
    var name: String = "",
    @JsonProperty("price")
    val price: Double = 0.0,
    @JsonProperty("big_image")
    val bigImageUrl: String = "",
    @JsonProperty("long_address")
    val longAddress: String = "",
)
