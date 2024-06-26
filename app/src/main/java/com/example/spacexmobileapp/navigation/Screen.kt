package com.example.spacexmobileapp.navigation

sealed class Screen(
    val route: String
) {
    data object Launch : Screen(LAUNCH_SCREEN)
    data object Main : Screen(MAIN_SCREEN)
    data object RocketsCompany : Screen(ROCKET_COMPANY_SCREEN)
    data object Crew : Screen(CREW_SCREEN)
    data object History : Screen(HISTORY_SCREEN)


    companion object {
        const val MAIN_SCREEN = "main"
        const val ROCKET_COMPANY_SCREEN = "rocket"
        const val CREW_SCREEN = "crew"
        const val HISTORY_SCREEN = "history"
        const val LAUNCH_SCREEN = "launch"
    }
}