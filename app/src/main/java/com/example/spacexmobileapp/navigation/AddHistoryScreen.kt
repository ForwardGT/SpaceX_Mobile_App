package com.example.spacexmobileapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.spacexmobileapp.presentation.history.HistoryScreen

fun NavGraphBuilder.addHistoryScreen(navController: NavController) {

    composable(Screen.History.route) {
        HistoryScreen()
    }
}