package com.example.mawqifi.features.parking.controller.model

import com.example.mawqifi.features.parking.service.dto.CreateAParkingDto
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateAParkingRequest(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("price")
    val price: Double,
    @JsonProperty("latitude")
    val latitude: Double,
    @JsonProperty("longitude")
    val longitude: Double,
    @JsonProperty("image")
    val image: String,
    @JsonProperty("type_id")
    val typeId: Int
) {
    fun toCreateAParkingDto(): CreateAParkingDto {
        return CreateAParkingDto(
            name = name,
            description = description,
            price = price,
            latitude = latitude,
            longitude = longitude,
            image = image,
            typeId = typeId
        )
    }
}
