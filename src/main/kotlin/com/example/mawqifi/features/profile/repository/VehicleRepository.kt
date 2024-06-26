package com.example.mawqifi.features.profile.repository

import com.example.mawqifi.features.profile.repository.entity.ProfileEntity
import com.example.mawqifi.features.profile.repository.entity.VehicleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository:JpaRepository<VehicleEntity,Long> {
    fun findAllByProfileEntity(profileEntity: ProfileEntity):List<VehicleEntity>
}
