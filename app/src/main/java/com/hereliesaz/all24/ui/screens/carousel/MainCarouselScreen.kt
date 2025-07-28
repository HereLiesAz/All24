@file:OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)

package com.hereliesaz.all24.ui.screens.carousel

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.ui.navigation.Screen
import kotlin.math.absoluteValue

const val FAKE_INFINITE_PAGE_COUNT = 10000

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

        val verticalPagerState = rememberPagerState(
            initialPage = FAKE_INFINITE_PAGE_COUNT / 2,
            pageCount = { FAKE_INFINITE_PAGE_COUNT }
        )

        VerticalPager(
            state = verticalPagerState,
            modifier = Modifier.fillMaxSize(),
            pageSize = PageSize.Fill,
        ) { page ->
            val categoryIndex = page % categories.size
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
    val horizontalPagerState = rememberPagerState(
        initialPage = if (places.isEmpty()) 0 else FAKE_INFINITE_PAGE_COUNT / 2,
        pageCount = { if (places.isEmpty()) 1 else FAKE_INFINITE_PAGE_COUNT }
    )

    with(sharedTransitionScope) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (places.isEmpty()) {
                Text("Nothing to see here... yet.")
            } else {
                HorizontalPager(
                    state = horizontalPagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clipToBounds(),
                    pageSize = PageSize.Fixed(300.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    pageSpacing = 8.dp
                ) { page ->
                    val placeIndex = page % places.size
                    val place = places[placeIndex]
                    val pageOffset =
                        ((horizontalPagerState.currentPage - page) + horizontalPagerState.currentPageOffsetFraction)
                    val translationX = lerp(
                        start = 0f,
                        stop = 150f,
                        fraction = (pageOffset - 1).coerceIn(-1f, 0f) + 1
                    ) + lerp(
                        start = 0f,
                        stop = -300f,
                        fraction = pageOffset.coerceIn(0f, 1f)
                    )
                    val scaleY = lerp(1f, 0.8f, pageOffset.absoluteValue)
                    val alpha = lerp(1f, 0.2f, pageOffset.absoluteValue.coerceIn(0f, 2f))
                    val interactionSource = remember { MutableInteractionSource() }
                    val isPressed by interactionSource.collectIsPressedAsState()
                    val pressScale by animateFloatAsState(
                        if (isPressed) 0.95f else 1f,
                        label = "pressScale"
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                this.translationX = translationX
                                this.scaleY = scaleY
                                this.alpha = alpha
                                this.scaleX = pressScale
                                this.scaleY *= pressScale // Combine scales
                            }
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { onPlaceClicked(place) }
                            )
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
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 64.dp)
            )
        }
    }
}