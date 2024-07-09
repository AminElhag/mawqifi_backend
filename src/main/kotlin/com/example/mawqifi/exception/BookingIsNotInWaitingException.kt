package com.example.mawqifi.exception

import org.apache.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.HttpStatusCodeException

class BookingIsNotInWaitingException :
    HttpStatusCodeException(HttpStatusCode.valueOf(HttpStatus.SC_CONFLICT), "Can't canceled booking after accessing it")