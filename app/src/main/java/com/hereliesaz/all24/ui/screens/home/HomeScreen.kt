package com.hereliesaz.all24.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hereliesaz.all24.data.model.Venue
import com.hereliesaz.all24.data.model.Vitals
import com.hereliesaz.all24.ui.navigation.Screen
import com.hereliesaz.all24.ui.theme.All24Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val items = (1..24).map {
        Venue(
            id = it,
            name = "Restaurant Name #$it",
            rank = it,
            summary = "A witty, Zagat-style summary line that encapsulates its spirit.",
            all24Take = "This is a 150-word official review, written by the in-house curators in the app's signature authoritative and witty style.",
            vitals = Vitals(
                address = "123 Fake St, New Orleans, LA",
                hours = "12:00 PM - 10:00 PM",
                phone = "(504) 555-1234"
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All24") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(items) { item ->
                ListItemCard(item = item, onClick = {
                    navController.navigate(Screen.Detail.createRoute(item.id))
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemCard(item: Venue, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "No. ${item.rank} of 24",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = item.summary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    All24Theme {
        HomeScreen(navController = rememberNavController())
    }
}
