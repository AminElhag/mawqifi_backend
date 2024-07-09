package com.example.mawqifi.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.HttpStatusCodeException

class BookingIsNotInProgressException :
HttpStatusCodeException(HttpStatusCode.valueOf(HttpStatus.CONFLICT.value()), "Can't completed booking when it is not in progress")
