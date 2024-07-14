package com.example.spacexmobileapp.presentation.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel : ViewModel() {

    val images = LinkCarouselObject.images

    private val _firstLoading = MutableStateFlow(true)
    val firstLoading = _firstLoading.asStateFlow()

    fun changeFirstLoadingState() {
        _firstLoading.value = !_firstLoading.value
    }
}
