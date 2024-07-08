package com.example.mawqifi.features.driver.controller

import com.example.mawqifi.features.auth.controller.model.OtpVerificationRequest
import com.example.mawqifi.features.auth.controller.model.PhoneNumberLoginRequest
import com.example.mawqifi.features.auth.service.AuthService
import com.example.mawqifi.features.booking.controller.model.GetBookingListResponse
import com.example.mawqifi.features.driver.controller.model.*
import com.example.mawqifi.features.driver.service.DriverService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/api/driver")
class DriverController {

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var driverService: DriverService

    @PostMapping("/profile")
    fun createDriver(@RequestBody request: CreateDriverRequest): ResponseEntity<CreatedDriverResponse> {
        val createProfileDto = driverService.createDriver(request.toCreateDriverDto())
        return ResponseEntity(createProfileDto.toCreatedDriverResponse(), HttpStatus.OK)
    }

    @PostMapping("/auth/mobile")
    fun phoneNumberLogin(@RequestBody request: PhoneNumberLoginRequest): ResponseEntity<Boolean> {
        authService.loginByPhoneNumber(request.toPhoneNumberDto())
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/auth/otp/verification")
    fun otpVerification(@RequestBody request: OtpVerificationRequest): ResponseEntity<OTPVerificationResponse?> {
        val verification = /*authService.otpVerification(request.toOtpVerificationDto())*/ true
        val token = if (verification) authService.token(request.phoneNumber, request.otp) else null
        val driverDto = if (verification) driverService.getDriverByPhoneNumber(request.phoneNumber) else null
        return ResponseEntity.ok(
            OTPVerificationResponse(
                isValid = verification,
                token = token,
                profile = driverDto?.toCreatedDriverResponse()
            )
        )
    }

    @GetMapping("/booking")
    fun getGetBookingRequestByType(
        @RequestParam("type_id") typeId: Int,
        @RequestParam("user_id") userId: Long
    ): ResponseEntity<List<GetBookingListResponse>?> {
        return ResponseEntity.ok(driverService.getBookingRequestByType(typeId, userId).map {
            it.toGetBookingListResponse()
        })
    }

    @PostMapping("/booking/accept")
    fun bookingAccept(
        @RequestBody request: BookingChangeStatusRequest
    ): ResponseEntity<Boolean> {
        driverService.bookingAccept(request.bookingId,request.driverId)
        return ResponseEntity.ok(true)
    }

    @PostMapping("/booking/complete")
    fun bookingComplete(
        @RequestBody request: BookingChangeStatusRequest
    ): ResponseEntity<Boolean> {
        driverService.bookingComplete(request.bookingId,request.driverId)
        return ResponseEntity.ok(true)
    }

    @PostMapping("/auth/fcmToken")
    fun setFcmToken(
        @RequestBody request: SetFcmTokenRequest
    ): ResponseEntity<Boolean> {
        driverService.setFcmToken(request.fcmToken,request.userId)
        return ResponseEntity.ok(true)
    }
}