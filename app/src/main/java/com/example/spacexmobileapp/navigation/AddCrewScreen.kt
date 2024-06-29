package com.example.spacexmobileapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.spacexmobileapp.presentation.crew.CrewScreen

fun NavGraphBuilder.addCrewScreen(navController: NavController) {

    composable(Screen.Crew.route) {
        CrewScreen()
    }
}