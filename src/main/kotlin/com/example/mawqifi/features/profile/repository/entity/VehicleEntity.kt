package com.example.mawqifi.features.profile.repository.entity

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val profileEntity: ProfileEntity = ProfileEntity()
)
