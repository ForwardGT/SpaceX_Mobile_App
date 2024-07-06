package com.example.spacexmobileapp.presentation.crew

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.spacexmobileapp.domain.entity.Astronaut
import com.example.spacexmobileapp.extensions.AppendStyledAstronaut
import com.example.spacexmobileapp.ui.theme.Black20
import com.example.spacexmobileapp.ui.theme.Gray10
import com.example.spacexmobileapp.utils.Constants
import com.example.spacexmobileapp.utils.CustomSpacer
import com.example.spacexmobileapp.utils.LocalDarkTheme

@Composable
fun CrewScreen() {

    val viewModel: CrewScreenViewModel = viewModel()
    val crew by viewModel.crew.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AstronautPost(crew)
        }
    }
}

@Composable
private fun AstronautPost(
    crew: List<Astronaut>
) {
    val isDarkTheme by LocalDarkTheme.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Constants.PADDINGS_TOP_30.dp),
    ) {
        items(
            crew,
            key = { it.id }
        ) { astronaut ->

            if (astronaut.id % 2 == 0) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = if (isDarkTheme) Black20 else Gray10)
                ) {
                    PhotoAstronaut(modifier = Modifier.weight(0.5f), astronaut)
                    CustomSpacer(start = 3)
                    InformationAstronaut(modifier = Modifier.weight(0.5f), astronaut)
                }
            } else {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = if (isDarkTheme) Black20 else Gray10)
                ) {
                    CustomSpacer(start = 3)
                    InformationAstronaut(modifier = Modifier.weight(0.5f), astronaut)
                    PhotoAstronaut(modifier = Modifier.weight(0.5f), astronaut)
                }
            }
        }
    }
}

@Composable
private fun PhotoAstronaut(
    modifier: Modifier,
    astronaut: Astronaut
) {
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.size(250.dp),
            model = astronaut.image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun InformationAstronaut(
    modifier: Modifier,
    astronaut: Astronaut
) {
    Box(modifier = modifier) {
        Text(
            text = buildAnnotatedString {
                AppendStyledAstronaut("Name:", astronaut.name)
                AppendStyledAstronaut("Status:", astronaut.status)
                AppendStyledAstronaut("Agency:", astronaut.agency)
                AppendStyledAstronaut("Wikipedia:", astronaut.wikipedia)
            },
        )
    }
}