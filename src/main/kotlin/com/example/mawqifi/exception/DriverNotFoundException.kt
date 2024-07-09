package com.example.mawqifi.exception

import org.apache.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.HttpStatusCodeException

class DriverNotFoundException :
    HttpStatusCodeException(HttpStatusCode.valueOf(HttpStatus.SC_NOT_FOUND), "Driver not found")