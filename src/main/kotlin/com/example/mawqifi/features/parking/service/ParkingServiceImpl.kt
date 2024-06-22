package com.example.mawqifi.features.parking.service

import com.example.mawqifi.features.parking.repository.ParkingRepository
import com.example.mawqifi.features.parking.repository.entity.ParkingEntity
import com.example.mawqifi.features.parking.service.dto.CreateAParkingDto
import com.example.mawqifi.features.parking.service.dto.ParkingDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ParkingServiceImpl : ParkingService {

    @Autowired
    lateinit var parkingRepository: ParkingRepository

    override fun getNearbyParking(latitude: Double, longitude: Double): List<ParkingDto> {
        var parking = parkingRepository.findAll()
        val map = parking.map {
            it.toParkingDto(latitude, longitude)
        }
        return map
    }

    override fun createAParking(createAParkingDto: CreateAParkingDto): Boolean {
        parkingRepository.save(
            ParkingEntity(
                name = createAParkingDto.name,
                description = createAParkingDto.description,
                latitude = createAParkingDto.latitude,
                longitude = createAParkingDto.longitude,
                price = createAParkingDto.price,
                imageUrl = createAParkingDto.image,
                typeId = createAParkingDto.typeId,
            )
        )
        return true
    }

    override fun getParkingByName(name: String, latitude: Double, longitude: Double): List<ParkingDto> {
        val entities = parkingRepository.findAllByNameContaining(name)
        println(entities)
        return entities.map {
            it.toParkingDto(latitude, longitude)
        }
    }
}