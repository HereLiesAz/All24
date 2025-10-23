package com.hereliesaz.all24.ui.screens.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hereliesaz.all24.data.model.Venue
import com.hereliesaz.all24.data.model.Vitals
import com.hereliesaz.all24.ui.theme.All24Theme
import androidx.compose.animation.SharedTransitionLayout

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    itemId: Int?,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    // TODO: Replace with a ViewModel to fetch the venue data.
    val venue = Venue(
        id = itemId ?: 0,
        name = "Restaurant Name ${itemId ?: 0}",
        rank = itemId ?: 0,
        summary = "A witty, Zagat-style summary line that encapsulates its spirit.",
        all24Take = "This is a 150-word official review, written by the in-house curators in the app's signature authoritative and witty style.",
        vitals = Vitals(
            address = "123 Fake St, New Orleans, LA",
            hours = "12:00 PM - 10:00 PM",
            phone = "(504) 555-1234"
        )
    )

    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = "item-${venue.id}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
        ) {
            Text(
                text = venue.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            // The All24 Take
            Text(
                text = "The All24 Take",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = venue.all24Take,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // The Vitals
            Text(
                text = "The Vitals",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Address: ${venue.vitals.address}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Hours: ${venue.vitals.hours}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Phone: ${venue.vitals.phone}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    All24Theme {
        SharedTransitionLayout {
            DetailScreen(
                itemId = 1,
                sharedTransitionScope = this,
                animatedVisibilityScope = it
            )
        }
    }
}