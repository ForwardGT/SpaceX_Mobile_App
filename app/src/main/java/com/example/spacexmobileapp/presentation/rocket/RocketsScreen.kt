package com.example.spacexmobileapp.presentation.rocket

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.spacexmobileapp.domain.entity.Rocket
import com.example.spacexmobileapp.extensions.AppendStyledAstronaut
import com.example.spacexmobileapp.ui.theme.Gray40
import com.example.spacexmobileapp.utils.Constants
import com.example.spacexmobileapp.utils.CustomSpacer
import kotlinx.coroutines.launch

@Composable
fun RocketScreen(
    navController: NavController
) {
    val viewModel: RocketScreenViewModel = viewModel()
    val rocketsList by viewModel.rockets.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(Modifier.fillMaxWidth()) {
                PostRockets(rocketsList = rocketsList)
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
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = rocket.name,
                    fontSize = Constants.FONT_SIZE_HEADER_MAIN.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                ImagePagerOneRocket(rocket = rocket)
                DescriptionRocket(rocket = rocket)
                CustomSpacer(top = Constants.PADDINGS_TOP_30)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImagePagerOneRocket(
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
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    },
                    error = {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
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
            NavigationIconButton(
                pagerState = pagerState,
                pageRout = 0,
                Icons.AutoMirrored.Filled.KeyboardArrowLeft
            )
            NavigationIconButton(
                pagerState = pagerState,
                pageRout = 1,
                Icons.AutoMirrored.Filled.KeyboardArrowRight
            )
        }
    }
}

@Composable
private fun DescriptionRocket(
    rocket: Rocket
) {
    Text(
        text = buildAnnotatedString {
            AppendStyledAstronaut("Name: ", rocket.name)
            AppendStyledAstronaut("First flight: ", rocket.firstFlight)
            AppendStyledAstronaut("Height: ", rocket.height.toString() + "m")
            AppendStyledAstronaut("Diameter: ", rocket.diameter.toString() + "m")
            AppendStyledAstronaut("Boosters: ", rocket.boosters.toString())
            AppendStyledAstronaut("Stages: ", rocket.stages.toString())
            AppendStyledAstronaut("Wikipedia: ", rocket.wikipedia)
        },
    )
    Text(
        textAlign = TextAlign.Center,
        text = buildAnnotatedString {
            AppendStyledAstronaut("Description for rocket:", "\n" + rocket.description)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NavigationIconButton(
    pagerState: PagerState,
    pageRout: Int,
    imageVector: ImageVector
) {
    val scope = rememberCoroutineScope()

    IconButton(
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(
                    pagerState.currentPage + if (pageRout == 1) 1 else -1
                )
            }
        }) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Gray40,
        )
    }
}