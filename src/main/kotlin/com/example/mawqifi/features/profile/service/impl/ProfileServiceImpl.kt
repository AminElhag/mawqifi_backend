package com.example.mawqifi.features.profile.service.impl

import com.example.mawqifi.exception.*
import com.example.mawqifi.features.profile.repository.ProfileRepository
import com.example.mawqifi.features.profile.repository.VehicleRepository
import com.example.mawqifi.features.profile.repository.entity.VehicleEntity
import com.example.mawqifi.features.profile.service.ProfileService
import com.example.mawqifi.features.profile.service.dto.CreateProfileDto
import com.example.mawqifi.features.profile.service.dto.CreateVehicleDto
import com.example.mawqifi.features.profile.service.dto.VehicleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProfileServiceImpl : ProfileService {

    @Autowired
    lateinit var profileRepository: ProfileRepository

    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    override fun createProfile(createProfileDto: CreateProfileDto): CreateProfileDto {
        val findByPhoneNumber = profileRepository.findByPhoneNumber(createProfileDto.phoneNumber)
        if (findByPhoneNumber != null) {
            return profileRepository.save(
                findByPhoneNumber.copy(
                    fullName = createProfileDto.fullName,
                    phoneNumber = createProfileDto.phoneNumber,
                    genderTypeId = createProfileDto.genderTypeId,
                    updateAt = Date(System.currentTimeMillis())
                )
            ).toCreateProfileDto()
        }
        val entity = profileRepository.save(createProfileDto.toProfileEntity())
        return entity.toCreateProfileDto()
    }

    override fun createVehicle(createVehicleDto: CreateVehicleDto) {
        val profileEntity = profileRepository.findByPhoneNumber(createVehicleDto.phoneNumber)
            ?: throw AddVehicleException()
        if (createVehicleDto.id != null) {
            val entityOptional = vehicleRepository.findById(createVehicleDto.id.toLong())
            if (entityOptional.isEmpty) {
                throw VehicleNotFoundException()
            }
            if (entityOptional.get().profileEntity != profileEntity) {
                throw VehicleProfileDoesNotMatch()
            }
            vehicleRepository.save(
                entityOptional.get().copy(
                    brand = createVehicleDto.brand,
                    model = createVehicleDto.model,
                    plantNo = createVehicleDto.plantNo,
                    color = createVehicleDto.color,
                    carTypeId = createVehicleDto.carTypeId,
                )
            )
        }else{
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

    override fun getVehiclesByUserId(userId: Long): List<VehicleDto> {
        val profile = profileRepository.findById(userId);
        if (profile.isEmpty) {
            throw ProfileNotFoundException()
        }
        val vehicleEntityList = vehicleRepository.findAllByProfileEntity(profile.get())
        if (vehicleEntityList.isEmpty()) {
            throw ProfileHaveNotAddAnyVehicle("This profile has not have any vehicle")
        }
        return vehicleEntityList.map {
            it.toVehicleDto()
        }
    }

    override fun getProfileByPhoneNumber(phoneNumber: String): CreateProfileDto? {
        return profileRepository.findByPhoneNumber(phoneNumber)?.toCreateProfileDto()
    }
}
