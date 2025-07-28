@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.hereliesaz.all24.ui.screens.place.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hereliesaz.all24.ui.screens.carousel.MainCarouselViewModel

@Composable
fun PlaceDetailScreen(
    placeId: String?,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val viewModel: MainCarouselViewModel = viewModel()
    val place = viewModel.uiState.value.placesByCategories.values
        .flatten()
        .find { it.id == placeId }

    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.sharedElement(
                    rememberSharedContentState(key = "title/$placeId"),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
                text = place?.name ?: "Place Not Found",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = place?.description ?: "No description available.",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}