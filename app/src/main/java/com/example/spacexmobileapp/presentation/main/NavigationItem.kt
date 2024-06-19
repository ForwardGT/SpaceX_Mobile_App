package com.example.spacexmobileapp.presentation.main

import com.example.spacexmobileapp.R
import com.example.spacexmobileapp.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val nameItem: String,
    val icon: Int
) {
    data object Rocket : NavigationItem(
        screen = Screen.Rocket,
        nameItem = "Rockets",
        icon = R.drawable.rockets_icon
    )

    data object Crew : NavigationItem(
        screen = Screen.Crew,
        nameItem = "Crew",
        icon = R.drawable.crew_icon
    )

    data object History : NavigationItem(
        screen = Screen.History,
        nameItem = "History",
        icon = R.drawable.history_icon
    )
}