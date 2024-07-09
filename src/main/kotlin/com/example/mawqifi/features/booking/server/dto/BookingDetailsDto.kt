package com.example.mawqifi.features.booking.server.dto

import com.example.mawqifi.features.booking.controller.model.BookingDetailsResponse
import com.example.mawqifi.features.driver.service.model.DriverDto
import com.example.mawqifi.features.parking.service.dto.ParkingDto
import com.example.mawqifi.features.profile.service.dto.VehicleDto
import java.util.*

data class BookingDetailsDto(
    val id: Long,
    val vehicle: VehicleDto,
    val parking: ParkingDto,
    val from: Date,
    val until: Date,
    val statusId: Int,
    val driver: DriverDto?,
) {
    fun toBookingDetailsResponse(): BookingDetailsResponse {
        return BookingDetailsResponse(
            id,vehicle.toVehicleResponse(),parking.toGetParkingRequest(),from,until,statusId,driver
        )
    }
}
