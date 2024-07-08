package com.example.mawqifi.features.driver.controller.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SetFcmTokenRequest(
    @JsonProperty("fcm_token")
    val fcmToken: String,
    @JsonProperty("user_id")
    val userId: Long
)
