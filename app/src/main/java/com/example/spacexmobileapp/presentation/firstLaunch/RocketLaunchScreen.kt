package com.example.spacexmobileapp.presentation.firstLaunch

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.spacexmobileapp.R
import kotlinx.coroutines.launch

@Composable
fun RocketLaunchScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val screenHeightPx = with(LocalDensity.current) { screenHeight.toPx() / 6 }
    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }

    val rocketOffsetY = remember { Animatable(screenHeightPx) }
    val coroutineScope = rememberCoroutineScope()
    var isLaunched by remember { mutableStateOf(false) }

    val imageSize = 900.dp

    fun launchRocket() {
        coroutineScope.launch {
            rocketOffsetY.snapTo(screenHeightPx)
            rocketOffsetY.animateTo(
                targetValue = (-imageSize * 3).value,
                animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_rocket_screen),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.rocket),
                contentDescription = "Rocket",
                modifier = Modifier
                    .size(imageSize)
                    .offset {
                        IntOffset(
                            ((-screenWidthPx / 2) + (screenWidth / 2).toPx()).toInt(),
                            (rocketOffsetY.value.toInt() + rocketOffsetY.value.toInt() / 8)
                        )
                    },
                contentScale = ContentScale.FillHeight
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                ),
                onClick = { isLaunched = true; launchRocket() }
            ) {
                Text("Launch")
            }
        }
    }
}