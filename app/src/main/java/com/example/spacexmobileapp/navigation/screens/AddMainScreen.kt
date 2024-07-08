package com.example.spacexmobileapp.navigation.screens

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.spacexmobileapp.navigation.Screen
import com.example.spacexmobileapp.presentation.main.MainScreen

fun NavGraphBuilder.addMainScreen(navController: NavController) {

//    composable(
//        "detail/{id}",
//        arguments = listOf(navArgument("id") { type = NavType.StringType })
//    ) { backStackEntry ->
//        MainScreen(id = backStackEntry.arguments?.getString("id"))
//    }


    composable(
        route = Screen.Main.route,
    ) { backStackEntry ->

        MainScreen(

            navController = navController
        )
    }
}


