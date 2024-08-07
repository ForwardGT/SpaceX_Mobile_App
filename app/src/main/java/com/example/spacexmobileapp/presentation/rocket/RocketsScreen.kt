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
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.spacexmobileapp.domain.entity.Rocket
import com.example.spacexmobileapp.extensions.AppendStyledText
import com.example.spacexmobileapp.ui.theme.Gray40
import com.example.spacexmobileapp.utils.Constants
import com.example.spacexmobileapp.utils.GradientLinkButton
import com.example.spacexmobileapp.utils.openLinkInBrowser
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RocketScreen(
    navController: NavController
) {
    val viewModel: RocketScreenViewModel = koinViewModel()
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
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Constants.PADDINGS_TOP_20.dp)
        ) {
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
    val context = LocalContext.current
    Text(
        text = buildAnnotatedString {
            AppendStyledText(label = "Name: ", value = rocket.name)
            AppendStyledText(label = "First flight: ", value = rocket.firstFlight)
            AppendStyledText(label = "Height: ", value = rocket.height.toString() + "m")
            AppendStyledText(label = "Diameter: ", value = rocket.diameter.toString() + "m")
            AppendStyledText(label = "Boosters: ", value = rocket.boosters.toString())
            AppendStyledText(
                label = "Stages: ",
                value = rocket.stages.toString(),
                newLineIsNeeded = false
            )
        },
    )
    Row {
        Text(
            text = buildAnnotatedString {
                AppendStyledText(
                    label = "Wikipedia:",
                    value = "",
                )
            }
        )
        GradientLinkButton(
            label = "Link",
            height = 20.dp,
            width = 50.dp
        ) {
            openLinkInBrowser(context, rocket.wikipedia)
        }

    }
    Text(
        textAlign = TextAlign.Center,
        text = buildAnnotatedString {
            AppendStyledText("Description for rocket:", "\n" + rocket.description)
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
        }
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Gray40,
        )
    }
}