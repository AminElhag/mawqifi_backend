package com.example.mawqifi.features.profile.service.dto

import com.example.mawqifi.features.profile.controller.model.VehicleResponse

data class VehicleDto(
    val brand: String,
    val model: String,
    val plantNo: String,
    val color: String,
    val carTypeId: Int,
    val id: Long
) {
    fun toVehicleResponse() = VehicleResponse(brand, model, plantNo, color, carTypeId, id)

}
