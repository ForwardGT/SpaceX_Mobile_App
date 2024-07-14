package com.example.spacexmobileapp.presentation.crew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexmobileapp.domain.entity.Astronaut
import com.example.spacexmobileapp.domain.repository.SpacexRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CrewScreenViewModel : ViewModel(), KoinComponent {

    private val repository: SpacexRepository by inject()

    private val _crew = MutableStateFlow<List<Astronaut>>(listOf())
    val crew = _crew.asStateFlow()

    init {
        loadCrew()
    }

    private fun loadCrew() {
        viewModelScope.launch {
            _crew.value = repository.loadCrew()
        }
    }
}