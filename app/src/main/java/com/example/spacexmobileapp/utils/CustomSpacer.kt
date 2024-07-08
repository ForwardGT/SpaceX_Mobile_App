package com.example.spacexmobileapp.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpacer(
    h: Dp = 0.dp,
    v: Dp = 0.dp,
) {
    Spacer(
        modifier = Modifier.padding(horizontal = h, vertical = v)
    )
}