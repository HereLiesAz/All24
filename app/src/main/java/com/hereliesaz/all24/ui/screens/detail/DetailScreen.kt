package com.hereliesaz.all24.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hereliesaz.all24.data.model.Venue
import com.hereliesaz.all24.data.model.Vitals
import com.hereliesaz.all24.ui.theme.All24Theme

@Composable
fun DetailScreen(itemId: Int?) {
    val venue = Venue(
        id = itemId ?: 0,
        name = "Restaurant Name #${itemId ?: 0}",
        rank = itemId ?: 0,
        summary = "A witty, Zagat-style summary line that encapsulates its spirit.",
        all24Take = "This is a 150-word official review, written by the in-house curators in the app's signature authoritative and witty style.",
        vitals = Vitals(
            address = "123 Fake St, New Orleans, LA",
            hours = "12:00 PM - 10:00 PM",
            phone = "(504) 555-1234"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "No. ${venue.rank} of 24",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = venue.name,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "The All24 Take",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = venue.all24Take,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "The Vitals",
            style = MaterialTheme.typography.titleLarge
        )
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

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    All24Theme {
        DetailScreen(itemId = 1)
    }
}
