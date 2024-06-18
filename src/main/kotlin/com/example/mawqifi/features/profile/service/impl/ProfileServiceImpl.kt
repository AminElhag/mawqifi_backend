package com.example.mawqifi.features.profile.service.impl

import com.example.mawqifi.exception.AddVehicleException
import com.example.mawqifi.features.profile.repository.ProfileRepository
import com.example.mawqifi.features.profile.repository.VehicleRepository
import com.example.mawqifi.features.profile.repository.entity.VehicleEntity
import com.example.mawqifi.features.profile.service.ProfileService
import com.example.mawqifi.features.profile.service.model.CreateProfileDto
import com.example.mawqifi.features.profile.service.model.CreateVehicleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl : ProfileService {

    @Autowired
    lateinit var profileRepository: ProfileRepository

    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    override fun createProfile(createProfileDto: CreateProfileDto): CreateProfileDto {
        val entity = profileRepository.save(createProfileDto.toProfileEntity())
        return entity.toCreateProfileDto()
    }

    override fun createVehicle(createVehicleDto: CreateVehicleDto) {
        val profileEntity = profileRepository.findByPhoneNumber(createVehicleDto.phoneNumber)
            ?: throw AddVehicleException("This number has not have a account")
        vehicleRepository.save(
            VehicleEntity(
                brand = createVehicleDto.brand,
                model = createVehicleDto.model,
                plantNo = createVehicleDto.plantNo,
                color = createVehicleDto.color,
                carTypeId = createVehicleDto.carTypeId,
                profileEntity = profileEntity
            )
        )
    }
}
