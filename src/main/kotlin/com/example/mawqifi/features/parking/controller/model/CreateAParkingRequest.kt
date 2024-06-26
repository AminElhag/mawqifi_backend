package com.example.mawqifi.features.parking.controller.model

import com.example.mawqifi.features.parking.service.dto.CreateAParkingDto
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateAParkingRequest(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("price")
    val price: Double,
    @JsonProperty("latitude")
    val latitude: Double,
    @JsonProperty("longitude")
    val longitude: Double,
    @JsonProperty("big_image_url")
    val bigImageUrl: String,
    @JsonProperty("type_id")
    val typeId: Int,
    @JsonProperty("images_url")
    val imagesUrl: ArrayList<String>,
    @JsonProperty("end_time")
    val endTime: Int,
    @JsonProperty("start_time")
    val startTime: Int,
    @JsonProperty("long_address")
    val longAddress: String,
    @JsonProperty("short_address")
    val shortAddress: String,
    @JsonProperty("ratting")
    val rating: Double,
    @JsonProperty("rules")
    val rules: String,
    @JsonProperty("space")
    val space: Double,
) {
    fun toCreateAParkingDto(): CreateAParkingDto {
        return CreateAParkingDto(
            name, price, latitude, longitude, bigImageUrl, typeId, imagesUrl, endTime, startTime, longAddress, shortAddress, rating, rules, space
        )
    }
}
