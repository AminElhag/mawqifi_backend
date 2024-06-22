package com.example.mawqifi.features.parking.controller

import com.example.mawqifi.features.parking.controller.model.CreateAParkingRequest
import com.example.mawqifi.features.parking.service.ParkingService
import com.example.mawqifi.features.parking.service.dto.ParkingDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/api/parking")
class ParkingController {

    @Autowired
    lateinit var parkingService: ParkingService

    @GetMapping
    fun getNearbyParking(
        @RequestParam name: String?,
        @RequestParam latitude: Double,
        @RequestParam longitude: Double
    ): List<ParkingDto> {
        return if (name.isNullOrBlank()) {
            parkingService.getNearbyParking(latitude, longitude)
        } else {
            parkingService.getParkingByName(name, latitude, longitude)
        }
    }

    @PostMapping("")
    fun createAParking(@RequestBody request: CreateAParkingRequest): ResponseEntity<Boolean> {
        parkingService.createAParking(request.toCreateAParkingDto())
        return ResponseEntity(true, HttpStatus.CREATED)
    }
}