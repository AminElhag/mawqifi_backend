package com.example.mawqifi.features.profile.service

import com.example.mawqifi.features.profile.service.model.CreateProfileDto
import com.example.mawqifi.features.profile.service.model.CreateVehicleDto

interface ProfileService {
    fun createProfile(createProfileDto: CreateProfileDto): CreateProfileDto
    fun createVehicle(createVehicleDto: CreateVehicleDto)

}
