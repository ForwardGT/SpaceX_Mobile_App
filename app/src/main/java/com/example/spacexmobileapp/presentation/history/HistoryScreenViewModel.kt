package com.example.spacexmobileapp.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexmobileapp.domain.entity.BlockHistory
import com.example.spacexmobileapp.domain.repository.SpacexRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HistoryScreenViewModel : ViewModel(), KoinComponent {

    private val repository: SpacexRepository by inject()

    private val _history = MutableStateFlow(listOf<BlockHistory>())
    val history = _history.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _history.value = repository.loadHistory()
        }
    }
}