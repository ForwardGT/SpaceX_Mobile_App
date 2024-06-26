package com.example.spacexmobileapp.presentation.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.spacexmobileapp.navigation.NavGraph
import com.example.spacexmobileapp.navigation.Screen
import com.example.spacexmobileapp.presentation.crew.CrewScreen
import com.example.spacexmobileapp.presentation.firstLaunch.RocketLaunchScreen
import com.example.spacexmobileapp.presentation.history.HistoryScreen
import com.example.spacexmobileapp.presentation.rocket.RocketScreen
import com.example.spacexmobileapp.ui.theme.SpaceXMobileAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()

        setContent {

            val viewModel: MainViewModel = viewModel()
            val stateDarkTheme by viewModel.darkTheme.collectAsState()

            val navController = rememberNavController()

            SpaceXMobileAppTheme(
                darkTheme = stateDarkTheme
            ) {
                NavGraph(
                    navController = navController,
                    mainScreenContent = {
                        MainScreen(
                            darkTheme = stateDarkTheme,
                            listenerUpdatedTheme = { viewModel.switchTheme() }
                        )
                    },
                    rocketScreenContent = { RocketScreen() },
                    historyScreenContent = { HistoryScreen() },
                    crewScreenContent = { CrewScreen() },
                    launchScreenContent = {
                        RocketLaunchScreen(
                            onClick = {
                                navController.navigate(Screen.Main.route)
                            }
                        )
                    }
                )
            }
        }
    }
}