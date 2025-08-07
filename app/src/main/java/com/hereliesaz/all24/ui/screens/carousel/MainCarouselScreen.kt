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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.ui.components.HorizontalMultiBrowsePager
import com.hereliesaz.all24.ui.components.ParentVerticalScrollConsumer
import com.hereliesaz.all24.ui.navigation.Screen
import com.hereliesaz.all24.ui.components.verticalcarousel.component.VerticalMultiBrowseCarousel
import kotlin.math.abs
import com.hereliesaz.all24.ui.components.verticalcarousel.state.rememberCarouselState as rememberVerticalCarouselState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

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
            flingBehavior = verticalCarouselState.fling(0f, spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )),
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
    Box(modifier = Modifier.fillMaxSize()) {
        // TODO: Re-implement shared element transition
        Text(
            text = category,
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 64.dp)
        )
        if (places.isEmpty()) {
            Text("Nothing to see here... yet.")
        } else {
            val pagerState = rememberPagerState(pageCount = { places.size })
            HorizontalMultiBrowsePager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .nestedScroll(ParentVerticalScrollConsumer)
                    .align(Alignment.Center),
                preferredItemWidth = 186.dp,
                itemSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) { page ->
                val place = places[page]
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
                                .padding(16.dp),
                            text = place.name,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}