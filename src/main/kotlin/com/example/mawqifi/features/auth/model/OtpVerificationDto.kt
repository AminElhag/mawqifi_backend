package com.example.mawqifi.features.auth.model

data class OtpVerificationDto(
    val phoneNumber: String,
    val otp: String,
)
