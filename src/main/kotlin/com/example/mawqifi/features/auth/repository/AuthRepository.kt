package com.example.mawqifi.features.auth.repository

import com.example.mawqifi.features.auth.repository.entity.MobilePhone
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRepository : JpaRepository<MobilePhone, Long> {
    fun findByPhoneNumber(phoneNumber: String):MobilePhone?
}
