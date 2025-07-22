package com.hereliesaz.all24.ui.screens.business

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.services.FirebaseService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class BusinessDashboardViewModel : ViewModel() {
    private val firebaseService = FirebaseService()
    private val userId = Firebase.auth.currentUser!!.uid

    val ownedPlaces: StateFlow<List<Place>> = firebaseService.getPlacesForOwnerStream(userId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessDashboardScreen(
    navController: NavController,
    viewModel: BusinessDashboardViewModel = viewModel()
) {
    val places by viewModel.ownedPlaces.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Businesses") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        LazyColumn(modifier = androidx.compose.ui.Modifier.padding(padding)) {
            items(places) { place ->
                ListItem(
                    headlineContent = { Text(place.name) },
                    supportingContent = { Text(place.description) },
                    modifier = androidx.compose.ui.Modifier.clickable {
                        // Navigate to an edit screen
                    }
                )
            }
        }
    }
}
