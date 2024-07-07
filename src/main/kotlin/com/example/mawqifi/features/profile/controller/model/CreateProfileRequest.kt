package com.example.mawqifi.features.profile.controller.model

import com.example.mawqifi.features.profile.service.dto.CreateProfileDto
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateProfileRequest(
    @JsonProperty("phone_number")
    val phoneNumber: String,
    @JsonProperty("full_name")
    val fullName: String,
    @JsonProperty("home_address")
    val homeAddress: String,
    @JsonProperty("gender_type_id")
    val genderTypeId: Int,
    @JsonProperty("platform_device_id")
    val platformDeviceId: String,
    @JsonProperty("platform_type")
    val platformType: String,
) {
    fun toCreateProfileDto(): CreateProfileDto {
        return CreateProfileDto(
            null,
            phoneNumber,
            fullName,
            homeAddress,
            genderTypeId,
            platformDeviceId,
            platformType,
            CreateProfileDto.ProfileType.USER.value
        )
    }
}