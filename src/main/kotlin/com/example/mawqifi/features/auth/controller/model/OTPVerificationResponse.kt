package com.example.mawqifi.features.auth.controller.model

import com.example.mawqifi.features.profile.controller.model.CreatedProfileResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class OTPVerificationResponse(
    @JsonProperty("is_valid")
    val isValid: Boolean,
    @JsonProperty("token")
    val token: String?,
    @JsonProperty("profile")
    val profile: CreatedProfileResponse?,
)
