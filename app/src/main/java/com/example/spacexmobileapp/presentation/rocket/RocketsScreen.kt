package com.example.spacexmobileapp.presentation.rocket

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.spacexmobileapp.domain.entity.Rocket
import com.example.spacexmobileapp.ui.theme.Gray40
import com.example.spacexmobileapp.utils.Constants
import com.example.spacexmobileapp.utils.CustomSpacer
import kotlinx.coroutines.launch

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
                Log.d("TAG", "NavigationButton: Recompos")
                PostRockets(rockets)
            }
        }
        BackHandler {
            navController.popBackStack()
        }
    }
}


@Composable
private fun PostRockets(
    rocketsList: List<Rocket>
) {
    if (rocketsList.isNotEmpty()) {
        LazyColumn {
            items(
                items = rocketsList,
                key = { it.name }
            ) { rocket ->
                OneRocket(rocket = rocket)
                DescriptionRocket(rocket = rocket)
                CustomSpacer(top = Constants.PADDINGS_TOP_20)
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


@Composable
private fun DescriptionRocket(
    rocket: Rocket
) {
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OneRocket(
    rocket: Rocket,
) {
    val pagerState = rememberPagerState(
        pageCount = { rocket.image.size }
    )

    Box {

        HorizontalPager(
            state = pagerState,
            key = { rocket.image[it] }
        ) { page ->

            Card {
                SubcomposeAsyncImage(
                    loading = {
                        CircularProgressIndicator()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Constants.ASYNC_IMAGE_HEIGHT.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(rocket.image[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = (Constants.ASYNC_IMAGE_HEIGHT / 2).dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            NavigationButton(pagerState)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NavigationButton(
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    IconButton(
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            tint = Gray40,
            modifier = Modifier.scale(1.3f)
        )
    }

    IconButton(
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Gray40,
            modifier = Modifier.scale(1.3f)
        )
    }
}