package com.example.spacexmobileapp.presentation.history

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spacexmobileapp.extensions.AppendStyledText
import com.example.spacexmobileapp.utils.Constants
import com.example.spacexmobileapp.utils.CustomSpacer

@Composable
fun HistoryScreen(
    navController: NavController
) {

    val viewModel: HistoryScreenViewModel = viewModel()
    val blockHistory by viewModel.history.collectAsState()


    Scaffold { paddingValue ->
        LazyColumn(
            modifier = Modifier.padding(paddingValue),
            verticalArrangement = Arrangement.spacedBy(Constants.PADDINGS_TOP_20.dp),
        ) {
            items(
                items = blockHistory,
                key = { it.title }
            ) { history ->
                if (blockHistory.isNotEmpty()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = history.title
                    )

                    CustomSpacer(v = 3.dp)
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = buildAnnotatedString {
                            AppendStyledText(
                                label = "Event date:",
                                value = history.eventDate,
                                fontStyleValue = FontStyle.Normal
                            )
                            AppendStyledText(
                                label = "Details:",
                                value = history.details,
                                fontStyleValue = FontStyle.Normal
                            )
                        }
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
        BackHandler {
            navController.popBackStack()
        }
    }
}