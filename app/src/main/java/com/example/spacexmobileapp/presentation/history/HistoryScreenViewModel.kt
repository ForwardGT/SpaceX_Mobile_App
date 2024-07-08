package com.example.spacexmobileapp.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexmobileapp.data.mapper.mapperHistory
import com.example.spacexmobileapp.data.network.ApiFactory
import com.example.spacexmobileapp.domain.entity.BlockHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryScreenViewModel : ViewModel() {

    private val _history = MutableStateFlow(listOf<BlockHistory>())
    val history = _history.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            val responseHistory = ApiFactory.apiService.getHistory()
            val postHistory = mapperHistory(responseHistory)
            _history.value = postHistory
        }
    }
}