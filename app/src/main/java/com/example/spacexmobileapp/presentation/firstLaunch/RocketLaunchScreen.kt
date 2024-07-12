package com.example.spacexmobileapp.presentation.firstLaunch

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.spacexmobileapp.R
import com.example.spacexmobileapp.navigation.Screen
import com.example.spacexmobileapp.presentation.main.HeaderText
import com.example.spacexmobileapp.ui.theme.Gradient03
import com.example.spacexmobileapp.ui.theme.Gradient04
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RocketLaunchScreen(
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val screenHeightPx = with(LocalDensity.current) { screenHeight.toPx() / 6 }
    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }

    val rocketOffsetY = remember { Animatable(screenHeightPx) }
    val imageSize = 900.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
                contentDescription = null,
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
            LaunchButton(
                navController = navController,
                label = "Launch",
                imageSize = imageSize,
                rocketOffsetY = rocketOffsetY,
                screenHeightPx = screenHeightPx
            )
        }
    }
}

@Composable
fun LaunchButton(
    navController: NavController,
    label: String,
    imageSize: Dp,
    screenHeightPx: Float,
    rocketOffsetY: Animatable<Float, AnimationVector1D>,
) {

    val scope = rememberCoroutineScope()
    var isLaunched by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                scope.launch {
                    isLaunched = true
                    launchRocket(
                        scope = scope,
                        screenHeightPx = screenHeightPx,
                        rocketOffsetY = rocketOffsetY, imageSize = imageSize
                    )
                    delay(1800) // Animation delay
                    navController.navigate(Screen.Main.route)
                }
            },
            modifier = Modifier
                .height(50.dp)
                .width(100.dp)
                .offset{
                       IntOffset(
                           x = 15, y = 0,
                       )
                },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Gradient03,
                                Gradient04
                            )
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HeaderText(
                    text = label,
                    centredText = true,
                    modifier = Modifier
                )
            }
        }
    }
}

fun launchRocket(
    imageSize: Dp,
    screenHeightPx: Float,
    rocketOffsetY: Animatable<Float, AnimationVector1D>,
    scope: CoroutineScope
) {
    scope.launch {
        rocketOffsetY.snapTo(screenHeightPx)
        rocketOffsetY.animateTo(
            targetValue = (-imageSize * 3).value,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
        )
    }
}