package com.hereliesaz.all24.ui.screens.business

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BusinessDashboardViewModel : ViewModel() {
    private val sheetsService = SheetsService()

    private val _ownedPlaces = MutableStateFlow<List<Place>>(emptyList())
    val ownedPlaces: StateFlow<List<Place>> = _ownedPlaces.asStateFlow()

    fun fetchOwnedPlaces(ownerId: String) {
        viewModelScope.launch {
            // _ownedPlaces.value = sheetsService.getPlacesForOwner(ownerId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessDashboardScreen(
    navController: NavController,
    viewModel: BusinessDashboardViewModel = viewModel()
) {
    val places by viewModel.ownedPlaces.collectAsState()

    // You would need to get the current user's ID from Google Sign-In to pass to the ViewModel
    // LaunchedEffect(Unit) {
    //     viewModel.fetchOwnedPlaces("current_user_id")
    // }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Businesses") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(places) { place ->
                ListItem(
                    headlineContent = { Text(place.name) },
                    supportingContent = { Text(place.description) },
                    modifier = Modifier.clickable {
                        // Navigate to an edit screen
                    }
                )
            }
        }
    }
}