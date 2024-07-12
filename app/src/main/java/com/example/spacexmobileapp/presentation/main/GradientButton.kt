package com.example.spacexmobileapp.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.spacexmobileapp.navigation.navigateTo
import com.example.spacexmobileapp.ui.theme.Gradient01
import com.example.spacexmobileapp.ui.theme.Gradient02

@Composable
fun GradientButton(
    navController: NavController,
    label: String,
    height: Dp = 60.dp,
    width: Dp = 0.dp,
    route: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { navController.navigateTo(route) },
            modifier = Modifier
                .padding(16.dp)
                .height(height)
                .then(
                    if (width > 0.dp) Modifier.width(width) else Modifier.fillMaxWidth()
                ),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Gradient01,
                                Gradient02
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