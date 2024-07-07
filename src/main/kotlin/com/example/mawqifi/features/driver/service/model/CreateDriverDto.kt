package com.example.mawqifi.features.driver.service.model

data class CreateDriverDto(
    val phoneNumber: String,
    val fullName: String,
    val homeAddress: String,
    val genderTypeId: Int,
    val platformDeviceId: String,
    val platformType: String,
    val fcmToken: String,
)
