package com.example.mawqifi.features.driver.repository

import com.example.mawqifi.features.driver.repository.entity.DriverEntity
import com.example.mawqifi.features.parking.repository.entity.ParkingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DriverRepository : JpaRepository<DriverEntity, Long> {
    fun findByPhoneNumber(phoneNumber: String): DriverEntity?
    fun findAllByParkingEntity(parkingEntity: ParkingEntity): List<DriverEntity?>?
}
