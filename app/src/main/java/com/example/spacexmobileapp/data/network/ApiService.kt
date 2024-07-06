package com.example.spacexmobileapp.data.network

import com.example.spacexmobileapp.data.model.CrewInformationDto
import com.example.spacexmobileapp.data.model.RocketInformationDto
import retrofit2.http.GET

interface ApiService {
    @GET("rockets")
    suspend fun getRocket(): List<RocketInformationDto>

    @GET("crew")
    suspend fun getCrew(): List<CrewInformationDto>

}
