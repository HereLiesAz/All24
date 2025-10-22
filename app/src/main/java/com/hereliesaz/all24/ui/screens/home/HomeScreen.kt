package com.hereliesaz.all24.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hereliesaz.all24.ui.theme.All24Theme

data class ListItem(val id: Int, val title: String, val rank: Int)

@Composable
fun HomeScreen() {
    val sampleData = List(24) { i -> ListItem(i, "Restaurant Name ${i + 1}", i + 1) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(sampleData) { item ->
            ListItemCard(item = item)
        }
    }
}

@Composable
fun ListItemCard(item: ListItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "No. ${item.rank} of 24",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "A witty, Zagat-style summary line that encapsulates its spirit.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    All24Theme {
        HomeScreen()
    }
}
