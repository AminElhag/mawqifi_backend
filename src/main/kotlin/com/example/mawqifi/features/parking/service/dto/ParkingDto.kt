package com.example.mawqifi.features.parking.service.dto

import com.example.mawqifi.features.parking.controller.model.GetParkingDetailsRequest
import com.example.mawqifi.features.parking.controller.model.ParkingResponse
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
    val imagesUri: List<String> = ArrayList(),
    @JsonProperty("latitude")
    val latitude: Double = 0.0,
    @JsonProperty("longitude")
    val longitude: Double = 0.0,
) {
    fun toGetParkingRequest() = ParkingResponse(
        parkingId, name, price, bigImageUrl, longAddress,latitude,longitude
    )

    fun toGetParkingDetailsRequest() = GetParkingDetailsRequest(
        parkingId, name, price, ratting, shortAddress, longAddress, space, startTime, endTime, rules, imagesUri
    )

    fun toMap(): Map<String, String> {
        return mapOf(
            "parking_id" to parkingId.toString(),
            "name" to name,
            "distance" to distance.toString(),
            "type_id" to typeId.toString(),
            "status_id" to statusId.toString(),
            "price" to price.toString(),
            "big_image_url" to bigImageUrl,
            "ratting" to ratting.toString(),
            "short_address" to shortAddress,
            "long_address" to longAddress,
            "space" to space.toString(),
            "start_time" to startTime.toString(),
            "end_time" to endTime.toString(),
            "rules" to rules,
            "images_uri" to imagesUri.toString()
        )
    }

}
