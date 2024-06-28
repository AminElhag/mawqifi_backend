package com.example.mawqifi.features.auth.controller

import com.example.mawqifi.features.auth.controller.model.OTPVerificationResponse
import com.example.mawqifi.features.auth.controller.model.OtpVerificationRequest
import com.example.mawqifi.features.auth.controller.model.PhoneNumberLoginRequest
import com.example.mawqifi.features.auth.service.AuthService
import com.example.mawqifi.features.profile.service.ProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var profileService: ProfileService


    @PostMapping("/mobile")
    fun phoneNumberLogin(@RequestBody request: PhoneNumberLoginRequest): ResponseEntity<Boolean> {
        authService.loginByPhoneNumber(request.toPhoneNumberDto())
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/otp/verification")
    fun otpVerification(@RequestBody request: OtpVerificationRequest): ResponseEntity<OTPVerificationResponse?> {
        val verification = authService.otpVerification(request.toOtpVerificationDto())
        val token = if (verification) authService.token(request.phoneNumber, request.otp) else null
        val profileDto = if (verification) profileService.getProfileByPhoneNumber(request.phoneNumber) else null
        return ResponseEntity.ok(
            OTPVerificationResponse(
                isValid = verification,
                token = token,
                profile = profileDto?.toCreatedProfileResponse()
            )
        )
    }
}