package com.example.mawqifi.features.profile.service.dto

import com.example.mawqifi.features.profile.controller.model.CreatedProfileResponse
import com.example.mawqifi.features.profile.repository.entity.ProfileEntity

data class CreateProfileDto(
    val userId:Long?=null,
    val phoneNumber: String,
    val fullName: String,
    val homeAddress: String,
    val genderTypeId: Int,
    val platformDeviceId: String,
    val platformType: String,
    val profileTypeId:Int
) {
    fun toProfileEntity(): ProfileEntity {
        return ProfileEntity(
            phoneNumber = phoneNumber,
            fullName = fullName,
            homeAddress = homeAddress,
            genderTypeId = genderTypeId,
            platformType = platformType,
            platformDeviceId = platformDeviceId,
            profileType = profileTypeId
        )
    }

    fun toCreatedProfileResponse(): CreatedProfileResponse {
        return CreatedProfileResponse(userId!!, phoneNumber, fullName, homeAddress, genderTypeId,profileTypeId)
    }

    enum class ProfileType(val value: Int) {
        USER(0), Driver(1)
    }
}
