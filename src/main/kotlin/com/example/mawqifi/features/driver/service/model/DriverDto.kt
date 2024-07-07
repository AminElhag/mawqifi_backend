package com.example.mawqifi.features.driver.service.model

import com.example.mawqifi.features.driver.controller.model.CreatedDriverResponse
import java.util.*

data class DriverDto(
    val id: Long = 0,
    val phoneNumber: String = "",
    val fullName: String = "",
    val homeAddress: String = "",
    val genderTypeId: Int = 0,
    val platformDeviceId: String = "",
    val platformType: String = "Unknown",
    val createAt: Date? = null,
    val updateAt: Date? = null,
    val fcmToken: String = "",
    val parkingId: Long? = null,
) {
    fun toCreatedDriverResponse() = CreatedDriverResponse(
        userId = id,
        phoneNumber = phoneNumber,
        fullName = fullName,
        homeAddress = homeAddress,
        genderTypeId = genderTypeId,
        parkingId = parkingId ?: 0
    )
}
