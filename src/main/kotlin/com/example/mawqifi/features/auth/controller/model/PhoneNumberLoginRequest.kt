package com.example.mawqifi.features.auth.controller.model

import com.example.mawqifi.features.auth.model.PhoneNumberDto
import com.fasterxml.jackson.annotation.JsonProperty

data class PhoneNumberLoginRequest(
    @JsonProperty("phone_number")
    val phoneNumber: String,
    @JsonProperty("os_type")
    val osType: String,
) {
    fun toPhoneNumberDto(): PhoneNumberDto {
        return PhoneNumberDto(phoneNumber, osType)
    }
}
