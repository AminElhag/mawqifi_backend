package com.example.mawqifi.features.auth.repository.entity

import jakarta.persistence.*
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
data class MobilePhone(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true, updatable = false, nullable = false)
    val phoneNumber: String = "",
    @Column(nullable = false)
    val otp: String = "",
    @Column(nullable = false)
    val expiryTime: Date = Date(System.currentTimeMillis()),
) {
    fun toUserDetails(): UserDetails? {
        return User.builder().username(phoneNumber).password("{noop}$otp").build()
    }
}
