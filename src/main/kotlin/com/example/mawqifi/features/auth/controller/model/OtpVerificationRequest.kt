package com.example.mawqifi.features.auth.controller.model

import com.example.mawqifi.features.auth.model.OtpVerificationDto
import com.fasterxml.jackson.annotation.JsonProperty

data class OtpVerificationRequest(
    @JsonProperty("phone_number")
    val phoneNumber: String,
    @JsonProperty("otp")
    val otp: String,
) {
    fun toOtpVerificationDto(): OtpVerificationDto = OtpVerificationDto(
        phoneNumber, otp
    )
}
