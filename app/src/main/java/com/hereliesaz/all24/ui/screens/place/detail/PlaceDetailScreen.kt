package com.hereliesaz.all24.ui.screens.place_detail

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(navController: NavController, placeId: String?) {
    // This is a placeholder screen.
    // A ViewModel would be created to fetch place and review details using the placeId.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Place Details") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Text("Details for place ID: $placeId", modifier = androidx.compose.ui.Modifier.padding(padding))
    }
}
