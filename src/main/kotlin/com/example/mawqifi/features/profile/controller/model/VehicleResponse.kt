package com.example.mawqifi.features.profile.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class VehicleResponse(
    @JsonProperty("brand")
    val brand: String,
    @JsonProperty("model")
    val model: String,
    @JsonProperty("plant_no")
    val plantNo: String,
    @JsonProperty("color")
    val color: String,
    @JsonProperty("car_type")
    val carTypeId: Int,
    @JsonProperty("vehicle_id")
    val id: Long
)
