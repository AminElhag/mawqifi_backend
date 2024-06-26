package com.example.mawqifi.features.booking.repository

import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.profile.repository.entity.ProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepository:JpaRepository<BookingEntity,Long> {
    fun findAllByProfileEntityOrderByCreateAtDesc(profileEntity:ProfileEntity):List<BookingEntity>
}
