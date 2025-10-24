package com.hereliesaz.all24.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hereliesaz.all24.data.model.Venue
import com.hereliesaz.all24.data.model.Vitals
import com.hereliesaz.all24.ui.theme.All24Theme

@Composable
fun DetailScreen(
    itemId: Int?
) {
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

    var comment1 by remember { mutableStateOf("") }
    var comment2 by remember { mutableStateOf("") }
    var comment3 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "No. ${venue.rank} of 24",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { /* TODO: Implement Vouch logic */ }) {
                Text("Vouch")
            }
        }
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
        Text(
            text = venue.all24Take,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Creator Takes
        Text(
            text = "Creator Takes",
            style = MaterialTheme.typography.titleLarge
        )
        // Placeholder for Creator Takes
        Text("Coming Soon")
        Spacer(modifier = Modifier.height(16.dp))

        // The Vitals
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
        Spacer(modifier = Modifier.height(16.dp))

        // The People's Voice
        Text(
            text = "The People's Voice",
            style = MaterialTheme.typography.titleLarge
        )
        // Placeholder for The People's Voice
        Text("Coming Soon")
        Spacer(modifier = Modifier.height(16.dp))

        // Know Before You Geaux
        Text(
            text = "Know Before You Geaux",
            style = MaterialTheme.typography.titleLarge
        )
        // Placeholder for Know Before You Geaux
        Text("Coming Soon")
        Spacer(modifier = Modifier.height(16.dp))

        // Vibe Check Submission
        Text(
            text = "Leave a Vibe Check",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = comment1,
                    onValueChange = { comment1 = it },
                    label = { Text("This place is perfect for...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = comment2,
                    onValueChange = { comment2 = it },
                    label = { Text("Don't even think about leaving without trying the...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = comment3,
                    onValueChange = { comment3 = it },
                    label = { Text("The vibe here is...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO: Implement comment submission */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    All24Theme {
        DetailScreen(itemId = 1)
    }
}
