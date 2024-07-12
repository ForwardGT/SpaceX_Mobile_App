package com.example.spacexmobileapp.presentation.galery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GalleryScreenViewModel : ViewModel() {
    private val _images = MutableStateFlow<List<String>>(emptyList())
    val images = _images.asStateFlow()

    private val _selectedImage = MutableStateFlow<String?>(null)
    val selectedImage: StateFlow<String?> = _selectedImage

    init {
        _images.value = GalleryImages.images
    }

    fun selectImage(image: String?) {
        _selectedImage.value = image
    }
}