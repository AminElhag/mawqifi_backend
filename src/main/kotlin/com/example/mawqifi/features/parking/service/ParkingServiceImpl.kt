package com.example.mawqifi.features.parking.service

import com.example.mawqifi.exception.ParkingNotFoundException
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
        val parking = parkingRepository.findAll()
        val map = parking.map {
            it.toParkingDto(latitude, longitude)
        }
        return map
    }

    override fun createAParking(createAParkingDto: CreateAParkingDto): Boolean {
        parkingRepository.save(
            ParkingEntity(
                name = createAParkingDto.name,
                latitude = createAParkingDto.latitude,
                longitude = createAParkingDto.longitude,
                typeId = createAParkingDto.typeId,
                price = createAParkingDto.price,
                bigImageUrl = createAParkingDto.bigImageUrl,
                ratting = createAParkingDto.rating,
                shortAddress = createAParkingDto.shortAddress,
                longAddress = createAParkingDto.longAddress,
                space = createAParkingDto.space,
                startTime = createAParkingDto.startTime,
                endTime = createAParkingDto.endTime,
                rules = createAParkingDto.rules,
                imagesUri = createAParkingDto.imagesUrl
            )
        )
        return true
    }

    override fun getParkingByName(name: String, latitude: Double, longitude: Double): List<ParkingDto> {
        val entities = parkingRepository.findAllByNameContains(name)
        println(entities)
        return entities.map {
            it.toParkingDto(latitude, longitude)
        }
    }

    override fun getParkingDetails(parkingId: Long): ParkingDto {
        val entityOptional = parkingRepository.findById(parkingId)
        if (entityOptional.isEmpty) {
            throw ParkingNotFoundException()
        }
        return entityOptional.get().toParkingDto()
    }
}