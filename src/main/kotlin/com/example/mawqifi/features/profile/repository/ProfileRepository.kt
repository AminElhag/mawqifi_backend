package com.example.mawqifi.features.profile.repository

import com.example.mawqifi.features.profile.repository.entity.ProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : JpaRepository<ProfileEntity, Long> {
    fun findByPhoneNumber(phoneNumber: String): ProfileEntity?
}
