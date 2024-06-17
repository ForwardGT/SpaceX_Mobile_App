package com.example.spacexmobileapp.presentation.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.spacexmobileapp.ui.theme.SpaceXMobileAppTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContent {
            var darkThemeActivate by remember { mutableStateOf(false) }

            SpaceXMobileAppTheme(
                darkTheme = darkThemeActivate
            ) {
                MainScreen(
                    onUpdatedTheme = { darkThemeActivate = !darkThemeActivate },
                    darkThemeActivate
                )
            }
        }
    }
}