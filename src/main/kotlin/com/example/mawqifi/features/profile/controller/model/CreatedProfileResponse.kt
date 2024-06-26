package com.example.mawqifi.features.profile.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CreatedProfileResponse(
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
)
