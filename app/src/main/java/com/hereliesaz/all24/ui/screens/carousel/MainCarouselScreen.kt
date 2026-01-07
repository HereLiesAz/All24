@file:OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class,
    ExperimentalMaterial3Api::class
)

package com.hereliesaz.all24.ui.screens.carousel

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.ui.navigation.Screen
import com.hereliesaz.verticalcarousel.component.VerticalMultiBrowseCarousel
import com.hereliesaz.verticalcarousel.state.rememberCarouselState as rememberVerticalCarouselState


@Composable
fun MainCarouselScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: MainCarouselViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val categories = uiState.categories
        val placesByCategories = uiState.placesByCategories

        val verticalCarouselState = rememberVerticalCarouselState(itemCount = { categories.size })

        VerticalMultiBrowseCarousel(
            state = verticalCarouselState,
            modifier = Modifier.fillMaxSize(),
            preferredItemHeight = 450.dp,
            itemSpacing = 16.dp,
        ) { categoryIndex ->
            val category = categories[categoryIndex]
            val places = placesByCategories[category] ?: emptyList()

            CategoryView(
                category = category,
                places = places,
                onPlaceClicked = { /* No-op for now */ },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}

@Composable
fun CategoryView(
    category: String,
    places: List<Place>,
    onPlaceClicked: (Place) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    // The complex HorizontalMultiBrowseCarousel has been replaced with a simple,
    // visible Box for debugging the parent VerticalMultiBrowseCarousel.
    with(sharedTransitionScope) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                // Use a fixed height to see how the parent places it.
                .height(400.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .border(2.dp, MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Category:",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = category,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(24.dp))
                Text(
                    text = "(${places.size} places)",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}