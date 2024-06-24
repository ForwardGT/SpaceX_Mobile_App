package com.example.spacexmobileapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexmobileapp.data.network.ApiFactory
import com.example.spacexmobileapp.domain.entity.Rocket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _darkTheme = MutableStateFlow(false)
    val darkTheme = _darkTheme.asStateFlow()

    private val _rockets = MutableStateFlow<List<Rocket>>(listOf())
    val rockets = _rockets.asStateFlow()

    fun switchTheme() {
        _darkTheme.value = !_darkTheme.value
    }


    fun getRocketAll() {
        viewModelScope.launch {
            val responseRocket = ApiFactory.apiService.getRocket()
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
            _rockets.value = result
        }
    }

}

