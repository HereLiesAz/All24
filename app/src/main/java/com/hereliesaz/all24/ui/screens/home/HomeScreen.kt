package com.hereliesaz.all24.ui.screens.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hereliesaz.all24.data.model.Venue
import com.hereliesaz.all24.data.model.Vitals
import com.hereliesaz.all24.ui.navigation.Screen
import com.hereliesaz.all24.ui.theme.All24Theme
import androidx.compose.animation.SharedTransitionLayout

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val sampleData = List(24) { i ->
        Venue(
            id = i,
            name = "Restaurant Name ${i + 1}",
            rank = i + 1,
            summary = "A witty, Zagat-style summary line that encapsulates its spirit.",
            all24Take = "This is a 150-word official review, written by the in-house curators in the app's signature authoritative and witty style.",
            vitals = Vitals(
                address = "123 Fake St, New Orleans, LA",
                hours = "12:00 PM - 10:00 PM",
                phone = "(504) 555-1234"
            )
        )
    }

    val listState = rememberLazyListState()
    val parallaxOffset by remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset * 0.5f
        }
    }

    Box {
        // Background with parallax effect
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationY = parallaxOffset
                },
            state = listState
        ) {
            item {
                Text(
                    "TEXTURED BACKGROUND PLACEHOLDER",
                    style = MaterialTheme.typography.displayLarge.copy(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                    modifier = Modifier.padding(32.dp)
                )
            }
        }

        // Foreground content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            state = listState
        ) {
            items(sampleData) { venue ->
                with(sharedTransitionScope) {
                    ListItemCard(
                        venue = venue,
                        animatedVisibilityScope = animatedVisibilityScope,
                        onClick = {
                            navController.navigate(Screen.Detail.createRoute(venue.id))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListItemCard(
    venue: Venue,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = "item-${venue.id}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
        ) {
            Text(
                text = "No. ${venue.rank} of 24",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = venue.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = venue.summary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    All24Theme {
        SharedTransitionLayout {
            HomeScreen(
                navController = rememberNavController(),
                sharedTransitionScope = this,
                animatedVisibilityScope = it
            )
        }
    }
}