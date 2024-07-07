package com.example.mawqifi.features.booking.repository.entity

import com.example.mawqifi.features.booking.server.dto.BookingDto
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    val vehicleEntity: VehicleEntity = VehicleEntity(),
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    val profileEntity: ProfileEntity = ProfileEntity(),
    @ManyToOne(fetch = FetchType.EAGER)
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
    @Column(name = "status_id")
    val statusId: Int = 0,
    @Column(name = "driver_id")
    val driverId: Long = 0,
) {
    fun toDto() = BookingDto(
        parkingId = id.toString(),
        userId = parkingEntity.id.toString(),
        vehicleId = vehicleEntity.id.toString(),
        from = from,
        until = until,
    )

    enum class Status(val id: Int) {
        WAITING(0), IN_PROGRESS(1), COMPLETED(2), CANCELED(3)
    }
}
