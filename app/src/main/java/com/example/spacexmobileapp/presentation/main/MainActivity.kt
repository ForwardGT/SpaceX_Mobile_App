package com.example.spacexmobileapp.presentation.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.spacexmobileapp.navigation.NavGraph
import com.example.spacexmobileapp.ui.theme.SpaceXMobileAppTheme
import com.example.spacexmobileapp.utils.LocalDarkTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()

        setContent {

            val isDarkTheme = remember { mutableStateOf(false) }
            val navController = rememberNavController()

            CompositionLocalProvider(value = LocalDarkTheme provides isDarkTheme) {
                SpaceXMobileAppTheme(darkTheme = isDarkTheme.value) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}