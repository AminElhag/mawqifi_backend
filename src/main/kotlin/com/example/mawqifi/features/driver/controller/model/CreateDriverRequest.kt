package com.example.mawqifi.features.driver.controller.model

import com.example.mawqifi.features.driver.service.model.CreateDriverDto
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateDriverRequest(
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
    @JsonProperty("fcm_token")
    val fcmToken: String,
) {
    fun toCreateDriverDto()= CreateDriverDto(phoneNumber, fullName, homeAddress, genderTypeId, platformDeviceId, platformType, fcmToken)
}
