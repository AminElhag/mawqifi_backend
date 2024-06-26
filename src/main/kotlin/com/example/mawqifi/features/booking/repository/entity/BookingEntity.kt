package com.example.mawqifi.features.booking.repository.entity

import com.example.mawqifi.features.parking.repository.entity.ParkingEntity
import com.example.mawqifi.features.profile.repository.entity.ProfileEntity
import com.example.mawqifi.features.profile.repository.entity.VehicleEntity
import jakarta.persistence.*
import java.util.*

@Entity
data class BookingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    val vehicleEntity: VehicleEntity = VehicleEntity(),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    val profileEntity: ProfileEntity = ProfileEntity(),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_id")
    val parkingEntity: ParkingEntity = ParkingEntity(),
    @Column(name = "from_cl")
    val from: Date = Date(System.currentTimeMillis()),
    @Column(name = "until_cl")
    val until: Date = Date(System.currentTimeMillis()),
    @Column(name = "create_at")
    val createAt: Date = Date(System.currentTimeMillis()),
    @Column(name = "update_at")
    val updateAt: Date = Date(System.currentTimeMillis()),
)
