package com.example.mawqifi.features.auth.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull
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
)
