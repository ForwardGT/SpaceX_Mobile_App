package com.example.spacexmobileapp.presentation.galery

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun GalleryScreen(
    navController: NavController
) {
    val viewModel: GalleryScreenViewModel = koinViewModel()

    val selectImage by viewModel.selectedImage.collectAsState()
    val images by viewModel.images.collectAsState()

    Scaffold { paddingValues ->

        LazyVerticalGrid(
            contentPadding = paddingValues,
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(6.dp)
        ) {
            items(items = images, key = { it }) { image ->
                PhotoGreed(image, viewModel)
            }
        }
        if (selectImage != null) {
            FullScreenPhoto(
                viewModel = viewModel,
                selectImage = selectImage
            )
        }
    }
    BackHandler {
        navController.popBackStack()
    }
}

@Composable
fun PhotoGreed(
    image: String,
    viewModel: GalleryScreenViewModel
) {
    AsyncImage(
        model = image,
        contentDescription = null,
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable { viewModel.selectImage(image) }
            .size(200.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun FullScreenPhoto(
    selectImage: String? = null,
    viewModel: GalleryScreenViewModel
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    val rememberTransformable = rememberTransformableState { zoom, pan, _->
        scale *= zoom
        offsetX += pan.x
        offsetY += pan.y
    }

    LaunchedEffect(rememberTransformable.isTransformInProgress) {
        if (!rememberTransformable.isTransformInProgress) {
            scale = 1f
            offsetX = 0f
            offsetY = 0f
            size = IntSize.Zero
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.9f))
            .transformable(rememberTransformable)
            .clickable { viewModel.selectImage(null) }
    ) {
        AsyncImage(
            model = selectImage,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
                .graphicsLayer(
                    translationX = offsetX,
                    translationY = offsetY,
                    scaleX = maxOf(1f, minOf(3f, scale)),
                    scaleY = maxOf(1f, minOf(3f, scale))
                )
        )
        BackHandler {
            viewModel.selectImage(null)
        }
    }
}