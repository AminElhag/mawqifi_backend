package com.example.mawqifi.features.auth.service

import com.example.mawqifi.features.auth.model.OtpVerificationDto
import com.example.mawqifi.features.auth.model.PhoneNumberDto

interface AuthService {
    fun loginByPhoneNumber(phoneNumberDto: PhoneNumberDto)
    fun otpVerification(otpVerificationDto: OtpVerificationDto): Boolean
    fun token(phoneNumber: String, otp: String): String
}