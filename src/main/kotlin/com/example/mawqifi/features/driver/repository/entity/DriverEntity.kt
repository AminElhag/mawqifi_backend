package com.example.mawqifi.features.driver.repository.entity

import com.example.mawqifi.features.driver.service.model.DriverDto
import com.example.mawqifi.features.parking.repository.entity.ParkingEntity
import jakarta.persistence.*
import java.util.*

@Entity
data class DriverEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false, unique = true)
    val phoneNumber: String = "",
    @Column(nullable = false)
    val fullName: String = "",
    @Column(nullable = false)
    val homeAddress: String = "",
    @Column(nullable = false)
    val genderTypeId: Int = 0,
    @Column(nullable = false)
    val platformDeviceId: String = "",
    @Column(nullable = false)
    val platformType: String = "Unknown",
    @Column(nullable = false)
    val createAt: Date? = null,
    @Column(nullable = false)
    val updateAt: Date? = null,
    @Column(nullable = false)
    val fcmToken: String = "",
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_id")
    val parkingEntity: ParkingEntity? = null,
) {
    fun toDriverDto() = DriverDto(
        id,
        phoneNumber,
        fullName,
        homeAddress,
        genderTypeId,
        platformDeviceId,
        platformType,
        createAt,
        updateAt,
        fcmToken,
        parkingEntity?.id
    )
}
