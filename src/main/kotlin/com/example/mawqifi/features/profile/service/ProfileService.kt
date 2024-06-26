package com.example.mawqifi.features.profile.service

import com.example.mawqifi.features.profile.service.dto.CreateProfileDto
import com.example.mawqifi.features.profile.service.dto.CreateVehicleDto
import com.example.mawqifi.features.profile.service.dto.VehicleDto

interface ProfileService {
    fun createProfile(createProfileDto: CreateProfileDto): CreateProfileDto
    fun createVehicle(createVehicleDto: CreateVehicleDto)
    fun getProfileByPhoneNumber(phoneNumber: String):CreateProfileDto?
    fun getVehiclesByUserId(userId: Long): List<VehicleDto>

}
