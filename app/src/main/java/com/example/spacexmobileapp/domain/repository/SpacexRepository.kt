package com.example.spacexmobileapp.domain.repository

import com.example.spacexmobileapp.domain.entity.Astronaut
import com.example.spacexmobileapp.domain.entity.BlockHistory
import com.example.spacexmobileapp.domain.entity.Rocket

interface SpacexRepository {

    suspend fun loadCrew(): List<Astronaut>

    suspend fun loadHistory(): List<BlockHistory>

    suspend fun loadRocket(): List<Rocket>

}