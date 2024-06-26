package com.example.mawqifi.features.parking.service

import com.example.mawqifi.features.parking.service.dto.CreateAParkingDto
import com.example.mawqifi.features.parking.service.dto.ParkingDto

interface ParkingService {
    fun getNearbyParking(latitude: Double, longitude: Double): List<ParkingDto>
    fun createAParking(createAParkingDto: CreateAParkingDto):Boolean
    fun getParkingByName(name: String, latitude: Double, longitude: Double): List<ParkingDto>
    fun getParkingDetails(parkingId: Long):ParkingDto

}
