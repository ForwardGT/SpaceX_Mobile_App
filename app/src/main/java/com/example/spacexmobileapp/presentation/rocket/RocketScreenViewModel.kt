package com.example.spacexmobileapp.presentation.rocket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexmobileapp.domain.entity.Rocket
import com.example.spacexmobileapp.domain.repository.SpacexRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RocketScreenViewModel : ViewModel(), KoinComponent {

    private val repository: SpacexRepository by inject()

    private val _rockets = MutableStateFlow<List<Rocket>>(listOf())
    val rockets = _rockets.asStateFlow()

    init {
        getRocket()
    }

    private fun getRocket() {
        viewModelScope.launch {
            _rockets.value = repository.loadRocket()
        }
    }
}