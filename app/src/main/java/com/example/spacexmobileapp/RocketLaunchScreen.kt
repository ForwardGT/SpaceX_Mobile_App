package com.example.spacexmobileapp

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RocketLaunchScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val screenHeightPx = with(LocalDensity.current) { screenHeight.toPx() }
    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }

    val rocketOffsetY = remember { Animatable(screenHeightPx) }
    val coroutineScope = rememberCoroutineScope()
    var isLaunched by remember { mutableStateOf(false) }

    val imageSize = 1000.dp

    fun launchRocket() {
        coroutineScope.launch {
            rocketOffsetY.snapTo(screenHeightPx)
            rocketOffsetY.animateTo(
                targetValue = (-imageSize * 3).value,
                animationSpec = tween(durationMillis = 2500, easing = LinearEasing)
            )
        }
    }

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
                        rocketOffsetY.value.toInt()
                    )
                },
            contentScale = ContentScale.FillHeight
        )
        Button(onClick = { isLaunched = true; launchRocket() }) {
            Text("Launch")
        }
    }
}