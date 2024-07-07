package com.example.mawqifi.features.driver.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CreatedDriverResponse(
    @JsonProperty("user_id")
    val userId: Long,
    @JsonProperty("phone_number")
    val phoneNumber: String,
    @JsonProperty("full_name")
    val fullName: String,
    @JsonProperty("home_address")
    val homeAddress: String,
    @JsonProperty("gender_type_id")
    val genderTypeId: Int,
    @JsonProperty("parking_id")
    val parkingId: Long,
)
