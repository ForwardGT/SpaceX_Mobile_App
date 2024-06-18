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
import com.example.spacexmobileapp.ui.theme.SpaceXMobileAppTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        enableEdgeToEdge()
        setContent {

            val viewModel = viewModel<MainVIewModel>()
            val stateDarkTheme by viewModel.darkTheme.collectAsState()

            SpaceXMobileAppTheme(
                darkTheme = stateDarkTheme
            ) {
                MainScreen(
                    listenerUpdatedTheme = { viewModel.switchTheme() },
                    stateDarkTheme
                )
            }
        }
    }
}