package com.example.mawqifi.features.booking.controller.model

import com.example.mawqifi.features.driver.service.model.DriverDto
import com.example.mawqifi.features.parking.controller.model.ParkingResponse
import com.example.mawqifi.features.profile.controller.model.VehicleResponse
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class BookingDetailsResponse(
    @JsonProperty("booking_id")
    val id: Long,
    @JsonProperty("vehicle")
    val vehicle: VehicleResponse,
    @JsonProperty("parking")
    val parking: ParkingResponse,
    @JsonProperty("from")
    val from: Date,
    @JsonProperty("to")
    val until: Date,
    @JsonProperty("status_id")
    val statusId: Int,
    @JsonProperty("driver")
    val driver: DriverDto? = null
)
