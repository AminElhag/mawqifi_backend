package com.example.mawqifi.features.parking.repository.entity

import com.example.mawqifi.common.Helper
import com.example.mawqifi.features.parking.service.dto.ParkingDto
import jakarta.persistence.*

@Entity(name = "parking_entity")
data class ParkingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    var name: String = "",
    @Column(nullable = false)
    var description: String = "",
    @Column(nullable = false)
    var latitude: Double = 0.0,
    @Column(nullable = false)
    var longitude: Double = 0.0,
    @Column(nullable = false)
    var typeId: Int = ParkingType.PUBLIC.typeId,
    @Column(nullable = false)
    var statusId: Int = 0,
    @Column(nullable = false)
    val price: Double = 0.0,
    @Column(nullable = false)
    val imageUrl: String = "",
) {
    fun toParkingDto(latitude: Double, longitude: Double): ParkingDto {
        return ParkingDto(
            name = name,
            description = description,
            typeId = typeId,
            price = price,
            imageUrl = imageUrl,
            statusId = statusId,
            distance = Helper.calculateDistance(latitude, longitude, this.latitude, this.longitude)
        )
    }

    enum class ParkingType(val typeId: Int) {
        PRIVATE(0), PUBLIC(1)
    }

    enum class ParkingStatusId(val statusId: Int) {
        AVAILABLE(0), UNAVAILABLE(1)
    }


}
