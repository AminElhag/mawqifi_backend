package com.example.mawqifi.features.booking.controller

import com.example.mawqifi.features.booking.controller.model.BookingIdResponse
import com.example.mawqifi.features.booking.controller.model.BookingDetailsResponse
import com.example.mawqifi.features.booking.controller.model.BookingRequest
import com.example.mawqifi.features.booking.controller.model.GetBookingListResponse
import com.example.mawqifi.features.booking.server.BookingServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/booking")
class BookingController {

    @Autowired
    lateinit var bookingServer: BookingServer

    @PostMapping
    fun bookingASpot(@RequestBody request: BookingRequest): ResponseEntity<BookingIdResponse> {
        return ResponseEntity.ok(BookingIdResponse(id =  bookingServer.save(request.toBookingDto()).id))
    }

    @GetMapping()
    fun getBookingList(@RequestParam(name = "profile_id") profileId: Long): ResponseEntity<List<GetBookingListResponse>> {
        val bookingList = bookingServer.getBookingList(profileId);
        return ResponseEntity.ok(bookingList.map { it.toGetBookingListResponse() })
    }

    @GetMapping("/details")
    fun getBookingDetails(@RequestParam(name = "booking_id") bookingId: Long): ResponseEntity<BookingDetailsResponse> {
        val bookingDetailsById = bookingServer.getBookingDetailsById(bookingId).toBookingDetailsResponse()
        return ResponseEntity.ok(bookingDetailsById)
    }

    @PostMapping("/canceled")
    fun cancelBooking(@RequestBody request:BookingIdResponse): ResponseEntity<Boolean> {
        bookingServer.canceledBooking(request.id)
        return ResponseEntity.ok(true)
    }

    @PostMapping("/completed")
    fun completedBooking(@RequestBody request:BookingIdResponse): ResponseEntity<Boolean> {
        bookingServer.completedBooking(request.id)
        return ResponseEntity.ok(true)
    }
}