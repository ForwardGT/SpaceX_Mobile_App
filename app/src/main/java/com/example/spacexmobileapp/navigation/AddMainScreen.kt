package com.example.spacexmobileapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.spacexmobileapp.presentation.main.MainScreen

fun NavGraphBuilder.addMainScreen(

    navController: NavController
) {
    composable(Screen.Main.route) {
        MainScreen(
            navController = navController
        )
    }
}