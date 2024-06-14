package com.example.spacexmobileapp.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacexmobileapp.R

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            SpacexLogoHeader()
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
private fun SpacexLogoHeader() {
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.size(width = 200.dp, height = 30.dp),
            painter = painterResource(id = R.drawable.spacex_mobile),
            contentDescription = "spaceX",
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = Modifier.padding(top = 3.dp),
            fontSize = 25.sp,
            text = "Mobile app",
            style = TextStyle(fontFamily = FontFamily.Cursive),
            fontWeight = FontWeight.SemiBold
        )
    }
}