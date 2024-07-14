package com.example.spacexmobileapp.data.repository

import com.example.spacexmobileapp.data.mapper.mapperCrew
import com.example.spacexmobileapp.data.mapper.mapperHistory
import com.example.spacexmobileapp.data.mapper.mapperRocket
import com.example.spacexmobileapp.data.network.ApiFactory
import com.example.spacexmobileapp.domain.entity.Astronaut
import com.example.spacexmobileapp.domain.entity.BlockHistory
import com.example.spacexmobileapp.domain.entity.Rocket
import com.example.spacexmobileapp.domain.repository.SpacexRepository

class RepositoryImpl : SpacexRepository {

    override suspend fun loadCrew(): List<Astronaut> {
        val responseCrew = ApiFactory.apiService.getCrew()
        val postCrew = mapperCrew(responseCrew)
        return postCrew
    }

    override suspend fun loadHistory(): List<BlockHistory> {
        val responseHistory = ApiFactory.apiService.getHistory()
        val postHistory = mapperHistory(responseHistory)
        return postHistory
    }

    override suspend fun loadRocket(): List<Rocket> {
        val responseRocket = ApiFactory.apiService.getRocket()
        val postRocket = mapperRocket(responseRocket)
        return postRocket
    }
}