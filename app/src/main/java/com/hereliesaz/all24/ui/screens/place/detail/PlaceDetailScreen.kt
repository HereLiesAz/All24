package com.hereliesaz.all24.ui.screens.place.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(navController: NavController, placeId: String?) {
    // A ViewModel would be created to fetch place and review details using the placeId.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Place Details") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Text("Details for place ID: $placeId", modifier = Modifier.padding(padding))
    }
}