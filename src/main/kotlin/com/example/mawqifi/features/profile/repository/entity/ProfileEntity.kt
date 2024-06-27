package com.example.mawqifi.features.profile.repository.entity

import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.profile.service.dto.CreateProfileDto
import jakarta.persistence.*
import java.util.*

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
    @OneToMany(mappedBy = "profileEntity", cascade = [CascadeType.ALL], orphanRemoval = true,fetch = FetchType.EAGER)
    val vehicleEntity: List<VehicleEntity?>? = null,
    @OneToMany(mappedBy = "profileEntity", cascade = [CascadeType.ALL], orphanRemoval = true,fetch = FetchType.EAGER)
    val bookingEntity: List<BookingEntity?>? = null,
) {
    fun toCreateProfileDto(): CreateProfileDto {
        return CreateProfileDto(
            id, phoneNumber, fullName, homeAddress, genderTypeId, platformDeviceId, platformType
        )
    }
}
