package com.example.mawqifi.features.booking.controller

import com.example.mawqifi.features.booking.controller.model.BookingRequest
import com.example.mawqifi.features.booking.server.BookingServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/booking")
class BookingController {

    @Autowired
    lateinit var server: BookingServer

    @PostMapping
    fun bookingASpot(@RequestBody request: BookingRequest): ResponseEntity<Boolean> {
        server.save(request.toBookingDto())
        return ResponseEntity.ok(true)
    }
}