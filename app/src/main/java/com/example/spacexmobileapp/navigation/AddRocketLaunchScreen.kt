package com.example.spacexmobileapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.spacexmobileapp.presentation.firstLaunch.RocketLaunchScreen

fun NavGraphBuilder.addRocketLaunchScreen(navController: NavController) {

    composable(Screen.Launch.route) {
        RocketLaunchScreen(navController)
    }
}