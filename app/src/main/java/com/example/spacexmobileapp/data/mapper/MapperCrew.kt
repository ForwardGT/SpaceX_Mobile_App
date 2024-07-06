package com.example.spacexmobileapp.data.mapper

import com.example.spacexmobileapp.data.model.CrewInformationDto
import com.example.spacexmobileapp.domain.entity.Astronaut

fun mapperCrew(
    responseCrew: List<CrewInformationDto>
): List<Astronaut> {

    val result = mutableListOf<Astronaut>()
    var idCount = 0

    responseCrew.forEach { item ->
        val astronaut = Astronaut(
            id = idCount,
            name = item.name,
            agency = item.agency,
            image = item.image,
            wikipedia = item.wikipedia,
            status = item.status
        )
        result.add(astronaut)
        idCount++
    }
    return result
}