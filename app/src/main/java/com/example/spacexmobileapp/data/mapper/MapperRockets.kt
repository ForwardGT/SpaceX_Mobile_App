package com.example.spacexmobileapp.data.mapper

import com.example.spacexmobileapp.data.model.RocketInformationDto
import com.example.spacexmobileapp.domain.entity.Rocket


fun mapperRocket(responseRocket: List<RocketInformationDto>): List<Rocket> {

    val result = mutableListOf<Rocket>()

    responseRocket.forEach { item ->
        val rocket = Rocket(
            name = item.name,
            height = item.height.meter,
            diameter = item.diameter.meter,
            stages = item.stages,
            boosters = item.boosters,
            wikipedia = item.wikipedia,
            description = item.description,
            image = item.image.first(),
            firstFlight = item.firstFlight
        )
        result.add(rocket)
    }
    return result
}