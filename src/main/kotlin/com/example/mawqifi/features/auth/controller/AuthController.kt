package com.example.mawqifi.features.auth.controller

import com.example.mawqifi.features.auth.controller.model.OtpVerificationRequest
import com.example.mawqifi.features.auth.controller.model.PhoneNumberLoginRequest
import com.example.mawqifi.features.auth.service.AuthService
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
    lateinit var authServiceImpl: AuthService

    @PostMapping("/mobile")
    fun phoneNumberLogin(@RequestBody request: PhoneNumberLoginRequest): ResponseEntity<Boolean> {
        authServiceImpl.loginByPhoneNumber(request.toPhoneNumberDto())
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/otp/verification")
    fun otpVerification(@RequestBody request: OtpVerificationRequest): ResponseEntity<Boolean> {
        val verification = authServiceImpl.otpVerification(request.toOtpVerificationDto())
        return ResponseEntity(verification, HttpStatus.OK)
    }
}