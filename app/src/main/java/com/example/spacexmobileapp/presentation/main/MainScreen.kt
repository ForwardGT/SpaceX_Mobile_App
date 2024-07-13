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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.spacexmobileapp.R
import com.example.spacexmobileapp.navigation.Screen
import com.example.spacexmobileapp.navigation.navigateTo
import com.example.spacexmobileapp.ui.theme.Purple100
import com.example.spacexmobileapp.utils.Constants
import com.example.spacexmobileapp.utils.LocalDarkTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(navController: NavController) {

    var isDarkTheme by LocalDarkTheme.current
    val viewModel: MainViewModel = koinViewModel()

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

                        val selected = navController.currentDestination?.hierarchy?.any {
                            it.route == item.screen.route
                        } ?: false

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navController.navigateTo(item.screen.route)
                                }

                            },
                            icon = {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = {
                                Text(text = item.nameItem)
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            MainScreenContent(
                isDarkTheme = isDarkTheme,
                viewModel = viewModel,
                navController = navController
            ) {
                isDarkTheme = !isDarkTheme
            }
        }
    }
}

@Composable
private fun MainScreenContent(
    navController: NavController,
    viewModel: MainViewModel,
    isDarkTheme: Boolean,
    isDarkThemeSwitcherListener: () -> Unit
) {
    SpacexLogoHeader(
        darkTheme = isDarkTheme,
        isDarkThemeSwitcherListener = isDarkThemeSwitcherListener
    )
    CarouselSlider(viewModel = viewModel)

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(R.string.description_company_spacex)
        )
        GradientButton(
            navController = navController,
            label = "Spacex Gallery",
            route = Screen.Gallery.route
        )
    }
}


@Composable
private fun SpacexLogoHeader(
    isDarkThemeSwitcherListener: () -> Unit,
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
            HeaderText(
                text = "Mobile App",
                modifier = Modifier.padding(top = Constants.PADDINGS_TOP_6.dp)
            )
        }
        Box(
            modifier = Modifier.weight(0.3f),
            contentAlignment = Alignment.CenterEnd
        ) {
            SwitcherTheme(
                isDarkThemeSwitcherListener = isDarkThemeSwitcherListener,
                darkTheme = darkTheme
            )
        }
    }
}


@Composable
private fun SwitcherTheme(
    darkTheme: Boolean,
    isDarkThemeSwitcherListener: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderText(
            text = if (darkTheme) "Dark " else "Light",
            modifier = Modifier.padding(top = Constants.PADDINGS_TOP_6.dp)
        )

        Switch(
            modifier = Modifier
                .scale(0.8f)
                .padding(top = Constants.PADDINGS_TOP_6.dp),
            checked = darkTheme,
            onCheckedChange = { isDarkThemeSwitcherListener() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Purple100,
            )
        )
    }
}

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier,
    centredText: Boolean = false
) {
    Text(
        modifier = modifier,
        fontSize = Constants.FONT_SIZE_HEADER_MAIN.sp,
        style = TextStyle(fontFamily = FontFamily.Cursive),
        fontWeight = FontWeight.SemiBold,
        textAlign = if (centredText) TextAlign.Center else null,
        text = text
    )
}
