package com.example.mawqifi.features.booking.repository

import com.example.mawqifi.features.booking.repository.entity.BookingEntity
import com.example.mawqifi.features.parking.repository.entity.ParkingEntity
import com.example.mawqifi.features.profile.repository.entity.ProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepository:JpaRepository<BookingEntity,Long> {
    fun findAllByProfileEntityOrderByCreateAtDesc(profileEntity:ProfileEntity):List<BookingEntity>
    fun findAllByStatusIdLessThan(statusId:Int):List<BookingEntity>
    fun findAllByStatusId(statusId:Int):List<BookingEntity>
    fun findAllByDriverIdAndStatusId(driverId:Long,statusId:Int):List<BookingEntity>
    fun findAllByParkingEntityAndStatusIdLessThan(parkingEntity: ParkingEntity, statusId: Int):List<BookingEntity>
}
