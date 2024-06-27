package com.example.mawqifi.features.profile.service.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateVehicleDto(
    @JsonProperty("phone_number")
    val phoneNumber: String,
    @JsonProperty("brand")
    val brand: String,
    @JsonProperty("model")
    val model: String,
    @JsonProperty("plant_no")
    val plantNo: String,
    @JsonProperty("color")
    val color: String,
    @JsonProperty("car_type_id")
    val carTypeId: Int,
    @JsonProperty("id")
    val id: Int?=null,
)