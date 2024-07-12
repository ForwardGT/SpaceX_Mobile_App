package com.example.spacexmobileapp.navigation.screens

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.spacexmobileapp.navigation.Screen
import com.example.spacexmobileapp.presentation.galery.GalleryScreen

fun NavGraphBuilder.addGalleryScreen(navController: NavController) {

    composable(Screen.Gallery.route) {
        GalleryScreen(navController)
    }
}