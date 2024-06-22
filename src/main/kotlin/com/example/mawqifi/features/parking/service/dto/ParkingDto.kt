package com.example.mawqifi.features.parking.service.dto

import com.example.mawqifi.features.parking.repository.entity.ParkingEntity.ParkingType
import org.locationtech.jts.geom.Geometry

data class ParkingDto(
    var name: String = "",
    var description: String = "",
    var distance: Double = 0.0,
    var typeId: Int = ParkingType.PUBLIC.typeId,
    var statusId: Int = 0,
    val price: Double = 0.0,
    val imageUrl: String = "",
)
