package com.example.mawqifi.features.profile.service.model

import com.example.mawqifi.features.profile.controller.model.CreatedProfileResponse
import com.example.mawqifi.features.profile.repository.entity.ProfileEntity

data class CreateProfileDto(
    val phoneNumber: String,
    val fullName: String,
    val homeAddress: String,
    val genderTypeId: Int,
    val platformDeviceId: String,
    val platformType: String,
) {
    fun toProfileEntity(): ProfileEntity {
        return ProfileEntity(
            phoneNumber = phoneNumber,
            fullName = fullName,
            homeAddress = homeAddress,
            genderTypeId = genderTypeId,
            platformType = platformType,
            platformDeviceId = platformDeviceId,
        )
    }

    fun toCreatedProfileResponse(): CreatedProfileResponse {
        return CreatedProfileResponse(phoneNumber, fullName, homeAddress, genderTypeId)
    }
}
