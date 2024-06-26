package com.example.spacexmobileapp.presentation.rocket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun RocketScreen() {

    Scaffold { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                Modifier.fillMaxWidth()
            ) {
                PostRocket()
            }
        }
    }
}

@Composable
fun PostRocket() {

    val viewModel: RocketScreenViewModel = viewModel()
    val rocket by viewModel.rockets.collectAsState()
    viewModel.getRocketAll()


    LazyColumn {
        items(
            items = rocket,
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
            Spacer(modifier = Modifier.padding(top = 20.dp))
        }
    }
}