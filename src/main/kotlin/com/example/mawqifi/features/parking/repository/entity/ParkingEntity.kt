package com.example.mawqifi.features.parking.repository.entity

import com.example.mawqifi.common.Helper
import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.driver.repository.entity.DriverEntity
import com.example.mawqifi.features.parking.service.dto.ParkingDto
import com.example.mawqifi.features.profile.repository.entity.VehicleEntity
import jakarta.persistence.*

@Entity(name = "parking_entity")
data class ParkingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    var name: String = "",
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
    val bigImageUrl: String = "",
    @Column(nullable = false)
    val ratting: Double = 0.0,
    @Column(nullable = false)
    val shortAddress: String = "",
    @Column(nullable = false)
    val longAddress: String = "",
    @Column(nullable = false)
    val space: Double = 0.0,
    @Column(nullable = false)
    val startTime: Int = 0,
    @Column(nullable = false)
    val endTime: Int = 0,
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    val rules: String = "",
    @ElementCollection(targetClass = String::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "images", joinColumns = [JoinColumn(name = "image_id")])
    @Column(name = "images", nullable = true)
    val imagesUri: List<String> = ArrayList(),
    /*@OneToMany(mappedBy = "parkingEntity",fetch = FetchType.EAGER)
    val bookingEntity: List<BookingEntity?>? = null,*/
    @OneToMany(mappedBy = "parkingEntity", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val driverEntity: List<DriverEntity?>? = null,
) {
    fun toParkingDto(latitude: Double? = null, longitude: Double? = null): ParkingDto {
        return ParkingDto(
            parkingId = id,
            name = name,
            typeId = typeId,
            price = price,
            bigImageUrl = bigImageUrl,
            statusId = statusId,
            distance = if (latitude != null && longitude != null) {
                Helper.calculateDistance(latitude, longitude, this.latitude, this.longitude)
            } else 0.0,
            ratting = ratting,
            startTime = startTime,
            endTime = endTime,
            space = space,
            shortAddress = shortAddress,
            longAddress = longAddress,
            rules = rules,
            imagesUri = imagesUri
        )
    }

    enum class ParkingType(val typeId: Int) {
        PRIVATE(0), PUBLIC(1)
    }

    enum class ParkingStatusId(val statusId: Int) {
        AVAILABLE(0), UNAVAILABLE(1)
    }


}
