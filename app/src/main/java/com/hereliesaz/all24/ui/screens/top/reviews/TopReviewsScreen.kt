package com.hereliesaz.all24.ui.screens.top.reviews

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopReviewsScreen(navController: NavController, viewModel: TopReviewsViewModel = viewModel()) {
    val reviews by viewModel.adminReviews.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top 24 Reviews") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        if (reviews.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("No admin reviews posted yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(reviews) { review ->
                    AdminReviewListItem(
                        review = review,
                        getPlace = { viewModel.getPlaceForReview(review) },
                        onClick = { placeId ->
                            navController.navigate(Screen.PlaceDetail.createRoute(placeId))
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun AdminReviewListItem(
    review: Review,
    getPlace: suspend () -> Place?,
    onClick: (String) -> Unit
) {
    var place by remember { mutableStateOf<Place?>(null) }

    LaunchedEffect(review.id) {
        place = getPlace()
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { place?.id?.let { onClick(it) } },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = place?.name ?: "Loading...",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = review.text,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
        }
    }
}
