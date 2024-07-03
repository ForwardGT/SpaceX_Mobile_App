package com.example.spacexmobileapp.presentation.rocket

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.spacexmobileapp.domain.entity.Rocket
import com.example.spacexmobileapp.utils.CustomSpacer

@Composable
fun RocketScreen(
    navController: NavController
) {
    val viewModel: RocketScreenViewModel = viewModel()
    val rockets by viewModel.rockets.collectAsState()

    Scaffold { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                Modifier.fillMaxWidth()
            ) {
                PostRocket(rockets)
            }
        }
        BackHandler {
            navController.popBackStack()
        }
    }
}

@Composable
fun PostRocket(
    rockets: List<Rocket>
) {

    if (rockets.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(
                items = rockets,
                key = { it.name }
            ) { rocket ->
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = rocket.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = "Name: ${rocket.name} \n" +
                            "First flight: ${rocket.firstFlight}\n" +
                            "Height: ${rocket.height}m.\n" +
                            "Diameter: ${rocket.diameter}m.\n" +
                            "Boosters: ${rocket.boosters}\n" +
                            "Stages: ${rocket.stages}\n" +
                            "Wikipedia: ${rocket.wikipedia}\n"
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "Description for rocket: \n ${rocket.description}"
                )
                CustomSpacer(top = 20)
            }
        }
    }
}