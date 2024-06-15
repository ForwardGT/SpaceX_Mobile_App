package com.example.spacexmobileapp.presentation.main


import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.spacexmobileapp.preview.PreviewFun
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuildLoginSlider() {

    val images = listOf(
        "https://media.gettyimages.com/id/1722894905/photo/psyche-spacecraft-launch.jpg?s=2048x2048&w=gi&k=20&c=PZSEJtIJJW3eamZ8BgMpDpDznHI6f5H2C-BxFJjOQD0=",
        "https://media.gettyimages.com/id/1251914828/photo/spacex-postpones-starship-launch.jpg?s=2048x2048&w=gi&k=20&c=N_96KJomJmSHk_DLZb0WUK3xkoVCTwrErUMNFgDQS8U=",
        "https://media.gettyimages.com/id/1242718935/photo/spacex-and-t-mobile-hold-joint-event-in-texas.jpg?s=2048x2048&w=gi&k=20&c=krUKkNuvmoUiUpmUGEisz-E5rYBjhELJxDxnIztDtPc=",
        "https://media.gettyimages.com/id/1251915379/photo/us-space-aerospace-spacex-starship.jpg?s=2048x2048&w=gi&k=20&c=toy3MWekk5uWEQVaGG3b6lr2MQry_ptVeR6Q1qIPtmc=",
        "https://media.gettyimages.com/id/521520676/photo/expedition-46-on-international-space-station.jpg?s=2048x2048&w=gi&k=20&c=Px9nGIIuwpTlVijLp_ofyNEv1n3ks_NcJ29tKljObeA=",
        "https://media.gettyimages.com/id/1723102931/photo/psyche-spacecraft-launch.jpg?s=2048x2048&w=gi&k=20&c=NU__Y1zpZ1f7wQxf7it3XJ9WZmNxGAVWYXkIFvhNxdU=",
        "https://media.gettyimages.com/id/1239843694/photo/spacex-axiom-1-launches-first-privately-funded-and-crewed-mission-to-iss.jpg?s=2048x2048&w=gi&k=20&c=94xMrMXttiztXD04hH62j-s_KdddYbNEOr73NrOqMRc=",
        "https://media.gettyimages.com/id/1243751894/photo/spacex-crewed-mission-launches-to-international-space-station.jpg?s=2048x2048&w=gi&k=20&c=SGu59xNmOd8xxrCsAmk8ZtzM2FBNQPsgCzIwDdB7s2g=",
        "https://media.gettyimages.com/id/914642586/photo/spacex-to-launch-first-heavy-lift-rocket-in-demonstration-mission.jpg?s=2048x2048&w=gi&k=20&c=KcSCTOWsRh31ITe8vioMTLSuV2KByga0cD8Kqi8jtvc=",
        "https://media.gettyimages.com/id/1046846338/photo/spacex-headquarters-in-hawthorne-california-as-a-long-exposure-shot.jpg?s=2048x2048&w=gi&k=20&c=-6iezabD5boCKiZte1SdzIElkvNv-k_1Bd9KPcLR1qo=",
    )
    val pagerState = rememberPagerState(pageCount = { images.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 150.dp),
        key = { images[it] },
        verticalAlignment = Alignment.CenterVertically
    ) { page ->
        Card(
            modifier = Modifier
                .size(300.dp)
                .graphicsLayer {
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(images[page])
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = "images",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit,
                clipToBounds = true
            )
        }
    }
}


@Preview
@Composable
fun prew1() {
    PreviewFun({ BuildLoginSlider() }, true)
}

@Preview
@Composable
fun prew2() {
    PreviewFun({ BuildLoginSlider() }, false)
}



