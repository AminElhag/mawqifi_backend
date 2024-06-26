package com.example.mawqifi.features.parking.service.dto

import com.example.mawqifi.features.parking.controller.model.GetParkingDetailsRequest
import com.example.mawqifi.features.parking.controller.model.GetParkingRequest
import com.example.mawqifi.features.parking.repository.entity.ParkingEntity.ParkingType
import com.fasterxml.jackson.annotation.JsonProperty

data class ParkingDto(
    @JsonProperty("parking_id")
    var parkingId: Long = 0,
    @JsonProperty("name")
    var name: String = "",
    @JsonProperty("distance")
    var distance: Double = 0.0,
    @JsonProperty("type_id")
    var typeId: Int = ParkingType.PUBLIC.typeId,
    @JsonProperty("status_id")
    var statusId: Int = 0,
    @JsonProperty("price")
    val price: Double = 0.0,
    @JsonProperty("big_image")
    val bigImageUrl: String = "",
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
    val imagesUri: List<String> = ArrayList()
) {
    fun toGetParkingRequest() = GetParkingRequest(
        parkingId, name, price, bigImageUrl, longAddress
    )

    fun toGetParkingDetailsRequest() = GetParkingDetailsRequest(
        parkingId, name, price, ratting, shortAddress, longAddress, space, startTime, endTime, rules, imagesUri
    )
}
