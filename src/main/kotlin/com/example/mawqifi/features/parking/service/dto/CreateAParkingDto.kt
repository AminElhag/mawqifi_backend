package com.example.mawqifi.features.parking.service.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateAParkingDto(
    val name: String,
    val price: Double,
    val latitude: Double,
    val longitude: Double,
    val bigImageUrl: String,
    val typeId: Int,
    val imagesUrl: ArrayList<String>,
    val endTime: Int,
    val startTime: Int,
    val longAddress: String,
    val shortAddress: String,
    val rating: Double,
    val rules: String,
    val space: Double,
)
