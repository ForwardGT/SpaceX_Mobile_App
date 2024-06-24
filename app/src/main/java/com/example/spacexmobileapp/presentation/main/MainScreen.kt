package com.example.spacexmobileapp.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacexmobileapp.R
import com.example.spacexmobileapp.preview.PreviewFun
import com.example.spacexmobileapp.ui.theme.Purple100

@Composable
fun MainScreen(
    listenerUpdatedTheme: () -> Unit,
    darkTheme: Boolean
) {
    Scaffold(
        bottomBar = {
            val barItem = listOf(
                NavigationItem.Rockets,
                NavigationItem.Crew,
                NavigationItem.History
            )
            NavigationBar(
                content = {
                    barItem.forEach { item ->
                        NavigationBarItem(
                            selected = false,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = { Text(text = item.nameItem) }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(paddingValues)
        ) {
            SpacexLogoHeader(
                darkTheme = darkTheme,
                clickListener = listenerUpdatedTheme
            )
            CarouselSlider()

            Box {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.description_company_spacex)
                )
            }
        }
    }
}


@Composable
private fun SpacexLogoHeader(
    clickListener: () -> Unit,
    darkTheme: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.weight(0.7f)
        ) {
            Image(
                modifier = Modifier.size(width = 150.dp, height = 30.dp),
                painter = painterResource(id = R.drawable.spacex_mobile),
                contentDescription = "spaceX",
                contentScale = ContentScale.FillWidth
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 19.sp,
                text = "Mobile app",
                style = TextStyle(fontFamily = FontFamily.Cursive),
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier.weight(0.3f),
            contentAlignment = Alignment.CenterEnd
        ) {
            SwitcherTheme(
                onClick = clickListener,
                darkTheme = darkTheme
            )
        }
    }
}

@Composable
private fun SwitcherTheme(
    darkTheme: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(top = 5.dp),
            style = TextStyle(fontFamily = FontFamily.Cursive),
            fontWeight = FontWeight.SemiBold,
            fontSize = 19.sp,
            text = if (darkTheme) "Dark " else "Light"
        )
        Switch(
            modifier = Modifier
                .scale(0.8f)
                .padding(top = 5.dp),
            checked = darkTheme,
            onCheckedChange = { onClick() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Purple100,
            )
        )
    }
}

@Preview
@Composable
fun Test() {
    PreviewFun(
        composableFun = { MainScreen(listenerUpdatedTheme = {}, darkTheme = true) },
        isDarkMode = true
    )
}