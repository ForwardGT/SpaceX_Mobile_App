package com.example.spacexmobileapp.presentation.main

import androidx.lifecycle.ViewModel
import com.example.spacexmobileapp.domain.entity.Rocket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _darkTheme = MutableStateFlow(false)
    val darkTheme = _darkTheme.asStateFlow()

    fun switchTheme() {
        _darkTheme.value = !_darkTheme.value
    }
}