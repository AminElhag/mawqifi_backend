package com.example.mawqifi.features.parking.controller.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.RoundingMode

data class GetParkingDetailsRequest(
    @JsonProperty("parking_id")
    var parkingId: Long = 0,
    @JsonProperty("name")
    var name: String = "",
    @JsonProperty("price")
    val price: Double = 0.0,
    @JsonProperty("ratting")
    val ratting: Double = 0.0,
    @JsonProperty("short_address")
    val shortAddress: String = "",
    @JsonProperty("long_address")
    val longAddress: String = "",
    @JsonProperty("space")
    val space: Double = 0.0,
    @JsonProperty("start_time")
    val startTime: Int = 0,
    @JsonProperty("end_time")
    val endTime: Int = 0,
    @JsonProperty("rules")
    val rules: String = "",
    @JsonProperty("images_url")
    val imagesUri: List<String> = ArrayList(),
    @JsonProperty("available_time_slices")
    val availableTimeSlices: List<Double> = arrayListOf(
        price,
        (price * 2),
        (price * 3).toBigDecimal().setScale(1, RoundingMode.HALF_UP).toDouble()
    ),
)
