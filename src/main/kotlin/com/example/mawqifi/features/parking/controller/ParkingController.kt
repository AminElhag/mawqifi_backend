package com.example.mawqifi.features.parking.controller

import com.example.mawqifi.exception.ParkingNotFoundException
import com.example.mawqifi.features.parking.controller.model.CreateAParkingRequest
import com.example.mawqifi.features.parking.controller.model.GetParkingDetailsRequest
import com.example.mawqifi.features.parking.controller.model.GetParkingRequest
import com.example.mawqifi.features.parking.service.ParkingService
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
    fun getParking(
        @RequestParam name: String?,
        @RequestParam latitude: Double,
        @RequestParam longitude: Double
    ): List<GetParkingRequest> {
        return if (name.isNullOrBlank()) {
            parkingService.getNearbyParking(latitude, longitude).map {
                it.toGetParkingRequest()
            }
        } else {
            parkingService.getParkingByName(name, latitude, longitude).map {
                it.toGetParkingRequest()
            }
        }
    }

    @GetMapping("/details")
    fun getParkingDetails(
        @RequestParam(name = "parking_id") parkingId: Long,
    ): ResponseEntity<GetParkingDetailsRequest> {
        return try {
            ResponseEntity(parkingService.getParkingDetails(parkingId).toGetParkingDetailsRequest(), HttpStatus.OK)
        } catch (e: ParkingNotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun createAParking(@RequestBody request: CreateAParkingRequest): ResponseEntity<Boolean> {
        parkingService.createAParking(request.toCreateAParkingDto())
        return ResponseEntity(true, HttpStatus.CREATED)
    }
}