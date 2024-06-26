package com.example.mawqifi.common

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
    @JsonProperty("status_code")
    val statusCode: Int,
    @JsonProperty("message")
    val message: String
)