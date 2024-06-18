package com.example.mawqifi.features.profile.repository.entity

import com.example.mawqifi.features.profile.service.model.CreateProfileDto
import jakarta.persistence.*
import java.util.*
import kotlin.collections.List

@Entity
data class ProfileEntity(
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
    val createAt: Date = Date(System.currentTimeMillis()),
    @Column(nullable = false)
    val updateAt: Date = Date(System.currentTimeMillis()),
    @OneToMany(mappedBy = "profileEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    val vehicleEntity: List<VehicleEntity?>? = null
) {
    fun toCreateProfileDto(): CreateProfileDto {
        return CreateProfileDto(
            phoneNumber, fullName, homeAddress, genderTypeId, platformDeviceId, platformType
        )
    }
}
