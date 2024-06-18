package com.example.mawqifi.features.auth.service.Impl

import com.example.mawqifi.features.auth.model.OtpVerificationDto
import com.example.mawqifi.features.auth.model.PhoneNumberDto
import com.example.mawqifi.features.auth.repository.AuthRepository
import com.example.mawqifi.features.auth.repository.entity.MobilePhone
import com.example.mawqifi.features.auth.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.*

@Service
class AuthServiceImpl : AuthService {

    @Value("\${otp.expiry.time}")
    private val otpExpiryTime: Int = 10400000

    @Autowired
    lateinit var authRepository: AuthRepository

    override fun loginByPhoneNumber(phoneNumberDto: PhoneNumberDto) {
        val otp = generateOtp(4)
        val phoneNumber = authRepository.findByPhoneNumber(phoneNumberDto.phoneNumber)
        if (phoneNumber != null) {
            authRepository.delete(phoneNumber)
        }
        print(Date(System.currentTimeMillis().plus(otpExpiryTime)))
        authRepository.save(
            MobilePhone(
                phoneNumber = phoneNumberDto.phoneNumber,
                otp = otp,
                expiryTime = Date(System.currentTimeMillis().plus(otpExpiryTime))
            )
        )
    }

    override fun otpVerification(otpVerificationDto: OtpVerificationDto): Boolean {
        val phoneNumber = authRepository.findByPhoneNumber(otpVerificationDto.phoneNumber) ?: return false
        return isValidOtp(otpVerificationDto, phoneNumber)
    }

    private fun isValidOtp(otpVerificationDto: OtpVerificationDto, phoneNumber: MobilePhone): Boolean {
        return phoneNumber.expiryTime.after(Date(System.currentTimeMillis())) && otpVerificationDto.otp == phoneNumber.otp
    }

    private fun generateOtp(length: Int): String {
        val secureRandom = SecureRandom()
        val otp = StringBuilder(length)
        for (i in 0 until length) {
            otp.append(secureRandom.nextInt(10)) // append a random digit (0-9)
        }
        return otp.toString()

    }
}
