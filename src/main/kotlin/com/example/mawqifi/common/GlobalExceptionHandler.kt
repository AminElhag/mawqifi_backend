package com.example.mawqifi.common

import com.example.mawqifi.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ProfileNotFoundException::class)
    fun handleProfileNotFoundException(e: ProfileNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message!!)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(AddVehicleException::class)
    fun handleAddVehicleException(e: AddVehicleException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message!!)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ParkingNotFoundException::class)
    fun handleParkingNotFoundException(e: ParkingNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message!!)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ProfileHaveNotAddAnyVehicle::class)
    fun handleProfileHaveNotAddAnyVehicleException(e: ProfileHaveNotAddAnyVehicle): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.CONFLICT.value(), e.message!!)
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(VehicleNotFoundException::class)
    fun handleVehicleNotFoundException(e: VehicleNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message!!)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(VehicleProfileDoesNotMatch::class)
    fun handleVehicleProfileDoesNotMatchException(e: VehicleProfileDoesNotMatch): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.CONFLICT.value(), e.message!!)
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }
}