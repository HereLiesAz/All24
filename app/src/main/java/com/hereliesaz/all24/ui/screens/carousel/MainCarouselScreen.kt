@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class,
    ExperimentalMaterial3Api::class
)

package com.hereliesaz.all24.ui.screens.carousel

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.ui.navigation.Screen
import com.hereliesaz.verticalcarousel.component.VerticalMultiBrowseCarousel
import kotlin.math.abs
import com.hereliesaz.verticalcarousel.state.rememberCarouselState as rememberVerticalCarouselState


@Composable
fun MainCarouselScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: MainCarouselViewModel = viewModel(),
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
                onPlaceClicked = { place ->
                    navController.navigate(Screen.PlaceDetail.createRoute(place.id))
                },
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
    with(sharedTransitionScope) {
        var horizontalDragInProgress by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                // The parent Box will only respond to vertical drags.
                // If a horizontal drag is detected, this pointerInput does nothing,
                // allowing the gesture to be handled by its children.
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        if (abs(dragAmount.x) < abs(dragAmount.y)) {
                            // This is a vertical drag, but we cannot manually control
                            // the parent, so we do nothing here. The parent's own
                            // handler will deal with it if the child doesn't.
                        } else {
                            // This is a horizontal drag.
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (places.isEmpty()) {
                Text("Nothing to see here... yet.")
            } else {
                HorizontalMultiBrowseCarousel(
                    state = rememberCarouselState { places.size },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    preferredItemWidth = 186.dp,
                    itemSpacing = 8.dp,
                    contentPadding = PaddingValues(horizontal = 16.dp),
                ) { placeIndex ->
                    val place = places[placeIndex]
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { onPlaceClicked(place) }
                            .maskClip(androidx.compose.material3.MaterialTheme.shapes.medium)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .sharedElement(
                                        rememberSharedContentState(key = "title/${place.id}"),
                                        animatedVisibilityScope = animatedVisibilityScope
                                    ),
                                text = place.name,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            Text(
                text = category,
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 64.dp)
            )
        }
    }
}