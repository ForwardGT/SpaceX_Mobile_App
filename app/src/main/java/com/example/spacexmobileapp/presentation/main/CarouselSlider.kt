package com.example.spacexmobileapp.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.spacexmobileapp.utils.Constants
import com.example.spacexmobileapp.utils.CustomSpacer
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselSlider(
    viewModel: MainScreenViewModel
) {
    val images = viewModel.images
    val firstState by viewModel.firstLoading.collectAsState()
    val pagerState = rememberPagerState(
        pageCount = { images.size }
    )

    if (firstState) {
        LaunchedEffect(pagerState) {
            pagerState.scrollToPage(images.size / 2)
            viewModel.changeFirstLoadingState()
        }
    }
    LaunchedEffect(pagerState) {
        if (pagerState.currentPage == 11) {
            pagerState.scrollToPage(1)
        }
    }

    Column {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 50.dp),
            key = { images[it] },
        ) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        lerp(
                            start = 0.9f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp),
                    contentScale = ContentScale.Crop,
                    clipToBounds = true
                )
            }
        }

        CustomSpacer(v = Constants.PADDINGS_TOP_6.dp)

        IndicatorsBallCarrousel(pagerState)
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun IndicatorsBallCarrousel(
    pagerState: PagerState
) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount) { iteration ->

            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
            )
        }
    }
}