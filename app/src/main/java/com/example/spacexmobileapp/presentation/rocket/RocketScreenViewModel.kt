package com.example.spacexmobileapp.presentation.rocket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexmobileapp.data.mapper.mapperRocket
import com.example.spacexmobileapp.data.network.ApiFactory
import com.example.spacexmobileapp.domain.entity.Rocket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RocketScreenViewModel : ViewModel() {

    private val _rockets = MutableStateFlow<List<Rocket>>(listOf())
    val rockets = _rockets.asStateFlow()

    fun getRocketAll() {
        viewModelScope.launch {
            val responseRocket = ApiFactory.apiService.getRocket()
            val postRocket = mapperRocket(responseRocket)
            _rockets.value = postRocket
        }
    }
}