package com.example.spacexmobileapp.navigation

import androidx.navigation.NavController

fun NavController.navigateTo(
    urlScreen: String
) {
    navigate(urlScreen) {
        launchSingleTop = true
    }
}