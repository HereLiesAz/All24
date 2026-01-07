@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.hereliesaz.all24.ui.screens.place.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PlaceDetailScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: PlaceDetailViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    with(sharedTransitionScope) {
        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(uiState.error ?: "An unknown error occurred.")
                }
            }

            uiState.place != null -> {
                val place = uiState.place!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.sharedElement(
                            rememberSharedContentState(key = "title/${place.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                        text = place.name,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = place.description,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    // TODO: Display reviews from uiState.reviews

                    com.hereliesaz.all24.ui.screens.detail.CreatorTake(
                        creatorName = "Jane Doe",
                        take = "This is the best place in town! You have to try the gumbo.",
                        onFollowClick = {}
                    )

                    com.hereliesaz.all24.ui.screens.detail.PeoplesVoice(
                        quote = "\"The best cocktails in the city, hands down.\"",
                        attribution = "- The People"
                    )
                }
            }
        }
    }
}