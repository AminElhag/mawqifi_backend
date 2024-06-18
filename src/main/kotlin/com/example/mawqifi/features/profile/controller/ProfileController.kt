package com.example.mawqifi.features.profile.controller

import com.example.mawqifi.exception.AddVehicleException
import com.example.mawqifi.features.profile.controller.model.CreateProfileRequest
import com.example.mawqifi.features.profile.controller.model.CreateVehicleRequest
import com.example.mawqifi.features.profile.controller.model.CreatedProfileResponse
import com.example.mawqifi.features.profile.service.ProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profile")
class ProfileController {

    @Autowired
    lateinit var profileService: ProfileService

    @PostMapping
    fun createProfile(@RequestBody request: CreateProfileRequest): ResponseEntity<CreatedProfileResponse> {
        val createProfileDto = profileService.createProfile(request.toCreateProfileDto())
        return ResponseEntity(createProfileDto.toCreatedProfileResponse(), HttpStatus.CREATED)
    }

    @PostMapping("/vehicle")
    fun createVehicle(@RequestBody request: CreateVehicleRequest): ResponseEntity<String> {
        try {
            profileService.createVehicle(request.toCreateVehicleDto())
            return ResponseEntity(HttpStatus.CREATED)
        } catch (e: AddVehicleException) {
            return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
        }
    }
}