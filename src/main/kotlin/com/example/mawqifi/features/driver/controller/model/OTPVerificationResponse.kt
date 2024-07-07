package com.example.mawqifi.features.driver.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OTPVerificationResponse(
    @JsonProperty("is_valid")
    val isValid: Boolean,
    @JsonProperty("token")
    val token: String?,
    @JsonProperty("drive")
    val profile: CreatedDriverResponse?,
)
