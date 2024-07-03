package com.example.mawqifi.features.parking.repository

import com.example.mawqifi.features.parking.repository.entity.ParkingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ParkingRepository:JpaRepository<ParkingEntity,Long> {
    fun findAllByNameContains(name: String): List<ParkingEntity>
}
