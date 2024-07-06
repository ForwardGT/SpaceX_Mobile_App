package com.example.spacexmobileapp.presentation.crew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexmobileapp.data.mapper.mapperCrew
import com.example.spacexmobileapp.data.network.ApiFactory
import com.example.spacexmobileapp.domain.entity.Astronaut
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CrewScreenViewModel : ViewModel() {

    private val _crew = MutableStateFlow<List<Astronaut>>(listOf())
    val crew = _crew.asStateFlow()

    init {
        loadCrew()
    }

    private fun loadCrew() {
        viewModelScope.launch {
            val responseCrew = ApiFactory.apiService.getCrew()
            val postCrew = mapperCrew(responseCrew)
            _crew.value = postCrew
        }
    }
}