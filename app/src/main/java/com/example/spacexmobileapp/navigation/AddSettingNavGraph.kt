package com.example.spacexmobileapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.addSettingNavGraph(
    navController: NavController
) {
    addRocketLaunchScreen(navController)
    addMainScreen(navController)
    addRocketScreen(navController)
    addCrewScreen(navController)
    addHistoryScreen(navController)
}