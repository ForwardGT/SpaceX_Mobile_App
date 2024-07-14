package com.example.spacexmobileapp.presentation.crew

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.spacexmobileapp.domain.entity.Astronaut
import com.example.spacexmobileapp.extensions.AppendStyledText
import com.example.spacexmobileapp.ui.theme.Black20
import com.example.spacexmobileapp.ui.theme.Gray10
import com.example.spacexmobileapp.utils.Constants
import com.example.spacexmobileapp.utils.CustomSpacer
import com.example.spacexmobileapp.utils.GradientLinkButton
import com.example.spacexmobileapp.utils.LocalDarkTheme
import com.example.spacexmobileapp.utils.openLinkInBrowser
import org.koin.androidx.compose.koinViewModel

@Composable
fun CrewScreen(navController: NavController) {

    val viewModel: CrewScreenViewModel = koinViewModel()
    val crew by viewModel.crew.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (crew.isNotEmpty()) {
                AstronautPost(crew)
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        BackHandler {
            navController.popBackStack()
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
                    CustomSpacer(h = 3.dp)
                    InformationAstronaut(modifier = Modifier.weight(0.5f), astronaut)
                }
            } else {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = if (isDarkTheme) Black20 else Gray10)
                ) {
                    CustomSpacer(h = 3.dp)
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
    val context = LocalContext.current
    Column(modifier = modifier) {
        Text(
            text = buildAnnotatedString {
                AppendStyledText(label = "Name:", value = astronaut.name)
                AppendStyledText(label = "Status:", value = astronaut.status)
                AppendStyledText(
                    label = "Agency:",
                    value = astronaut.agency,
                    newLineIsNeeded = false
                )
            }
        )
        Row {
            Text(
                text = buildAnnotatedString {
                    AppendStyledText(
                        label = "Wikipedia:",
                        value = "",
                        newLineIsNeeded = false
                    )
                }
            )
            GradientLinkButton(
                label = "Link",
                height = 20.dp,
                width = 50.dp
            ) {
                openLinkInBrowser(context, astronaut.wikipedia)
            }
        }
    }
}