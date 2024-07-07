package com.example.mawqifi

import com.example.mawqifi.common.ErrorResponse
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@EnableScheduling
class MawqifiApplication

fun main(args: Array<String>) {
    runApplication<MawqifiApplication>(*args)
}

@RestController
class MyErrorController : ErrorController {

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)

        if (status != null) {
            val statusCode = status.toString().toInt()

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), "Sorry, we can found that 💩")
                return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                val errorResponse = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sorry, that is our problem 🐙")
                return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        val errorResponse = ErrorResponse(HttpStatus.FORBIDDEN.value(), "You are not allow to do that")
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
