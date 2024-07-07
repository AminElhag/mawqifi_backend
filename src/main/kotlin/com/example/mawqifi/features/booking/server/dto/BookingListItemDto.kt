package com.example.mawqifi.features.booking.server.dto

import com.example.mawqifi.features.booking.controller.model.GetBookingListResponse
import com.example.mawqifi.features.parking.repository.entity.ParkingEntity
import com.example.mawqifi.features.profile.repository.entity.VehicleEntity
import java.util.*

data class BookingListItemDto(
    val id: Long,
    val imageUrl: String,
    val name: String,
    val longAddress: String,
    val price: Double,
    val from: Date,
    val until: Date,
    val statusId: Int,
    val parkingEntity: ParkingEntity?=null,
    val vehicleEntity: VehicleEntity?=null
) {
    fun toGetBookingListResponse() = GetBookingListResponse(
        id = id,
        imageUrl = imageUrl,
        name = name,
        longAddress = longAddress,
        price = price,
        from = from,
        until = until,
        statusId = statusId,
        vehicleResponse = vehicleEntity?.toVehicleDto()?.toVehicleResponse()
    )
}
