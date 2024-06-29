package com.example.spacexmobileapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.spacexmobileapp.presentation.rocket.RocketScreen

fun NavGraphBuilder.addRocketScreen(navController: NavController) {

    composable(Screen.RocketsCompany.route) {
        RocketScreen()
    }
}