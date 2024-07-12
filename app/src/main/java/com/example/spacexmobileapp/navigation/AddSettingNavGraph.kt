package com.example.spacexmobileapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.spacexmobileapp.navigation.screens.addCrewScreen
import com.example.spacexmobileapp.navigation.screens.addGalleryScreen
import com.example.spacexmobileapp.navigation.screens.addHistoryScreen
import com.example.spacexmobileapp.navigation.screens.addMainScreen
import com.example.spacexmobileapp.navigation.screens.addRocketLaunchScreen
import com.example.spacexmobileapp.navigation.screens.addRocketScreen

fun NavGraphBuilder.addSettingNavGraph(
    navController: NavController
) {
    addRocketLaunchScreen(navController)
    addMainScreen(navController)
    addRocketScreen(navController)
    addCrewScreen(navController)
    addHistoryScreen(navController)
    addGalleryScreen(navController)
}