package com.example.spacexmobileapp.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpacer(
    top: Int = 0,
    bottom: Int = 0,
    end: Int = 0,
    start: Int = 0
) {
    Spacer(
        modifier = Modifier.padding(
            top = top.dp,
            bottom = bottom.dp,
            end = end.dp,
            start = start.dp
        )
    )
}