package com.example.spacexmobileapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController: NavHostController,
    mainScreenContent: @Composable () -> Unit,
    rocketScreenContent: @Composable () -> Unit,
    historyScreenContent: @Composable () -> Unit,
    crewScreenContent: @Composable () -> Unit,
    launchScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Launch.route
    ) {
        composable(Screen.Main.route) {
            mainScreenContent()
        }
        composable(Screen.RocketsCompany.route) {
            rocketScreenContent()
        }
        composable(Screen.History.route) {
            historyScreenContent()
        }
        composable(Screen.Crew.route) {
            crewScreenContent()
        }
        composable(Screen.Launch.route) {
            launchScreenContent ()
        }
    }
}