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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hereliesaz.all24.data.model.Venue
import com.hereliesaz.all24.data.model.Vitals
import com.hereliesaz.all24.ui.theme.All24Theme
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale

@Composable
fun DetailScreen(
    itemId: Int?,
    detailViewModel: DetailViewModel = viewModel()
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

    val comment1 by detailViewModel.comment1.collectAsState()
    val comment2 by detailViewModel.comment2.collectAsState()
    val comment3 by detailViewModel.comment3.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "No. ${venue.rank} of 24",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )
            VouchButton()
        }
        Text(
            text = venue.name,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        // The All24 Take
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "The All24 Take",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = venue.all24Take,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Creator Takes (Carousel Placeholder)
        Text(
            text = "Creator Takes",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth().height(120.dp)) {
             Text("Creator Video/Photo Carousel Coming Soon", modifier = Modifier.padding(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))

        // The Vitals
        Text(
            text = "The Vitals",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "📍 ${venue.vitals.address}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "🕒 ${venue.vitals.hours}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "📞 ${venue.vitals.phone}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Vibe Check Submission (Mad Libs)
        Text(
            text = "Vibe Check",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                MadLibField(
                    prefix = "This place is perfect for ",
                    suffix = " with ",
                    value = comment1,
                    onValueChange = { detailViewModel.onComment1Changed(it) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                MadLibField(
                    prefix = "Don't even think about leaving without trying the ",
                    suffix = ". It tastes like ",
                    value = comment2,
                    onValueChange = { detailViewModel.onComment2Changed(it) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                MadLibField(
                    prefix = "The vibe here is ",
                    suffix = ", especially when ",
                    value = comment3,
                    onValueChange = { detailViewModel.onComment3Changed(it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { detailViewModel.submitComments() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Vouch & Submit Vibe Check")
                }
            }
        }
    }
}

@Composable
fun VouchButton() {
    var vouched by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (vouched) 1.2f else 1.0f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 200f)
    )

    Button(
        onClick = { vouched = !vouched },
        modifier = Modifier.scale(scale)
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = if (vouched) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(if (vouched) "Vouched!" else "Vouch")
    }
}

@Composable
fun MadLibField(prefix: String, suffix: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = prefix, style = MaterialTheme.typography.labelLarge)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium
        )
        Text(text = suffix, style = MaterialTheme.typography.labelLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    All24Theme {
        DetailScreen(itemId = 1)
    }
}
