package com.example.mawqifi.features.profile.repository.entity

import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.profile.service.dto.VehicleDto
import jakarta.persistence.*

@Entity
data class VehicleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val brand: String = "",
    @Column
    val model: String = "",
    @Column
    val plantNo: String = "",
    @Column
    val color: String = "",
    @Column
    val carTypeId: Int = 0,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    val profileEntity: ProfileEntity = ProfileEntity(),
    @OneToMany(mappedBy = "vehicleEntity", cascade = [CascadeType.ALL], orphanRemoval = true,fetch = FetchType.EAGER)
    val bookingEntity: List<BookingEntity?>? = null

) {
    fun toVehicleDto() = VehicleDto(brand, model, plantNo, color, carTypeId, id)
}
