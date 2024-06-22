package com.example.mawqifi.features.parking.service.dto

data class CreateAParkingDto(
    val name: String,
    val description: String,
    val price: Double,
    val latitude: Double,
    val longitude: Double,
    val typeId: Int,
    val image: String
)
