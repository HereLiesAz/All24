package com.hereliesaz.all24.ui.screens.business

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.services.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditPlaceViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val firebaseService = FirebaseService()
    private val placeId: String = savedStateHandle.get<String>("placeId")!!

    private val _place = MutableStateFlow<Place?>(null)
    val place = _place.asStateFlow()

    init {
        viewModelScope.launch {
            _place.value = firebaseService.getPlaceById(placeId)
        }
    }

    fun onNameChange(name: String) {
        _place.value = _place.value?.copy(name = name)
    }

    fun onDescriptionChange(description: String) {
        _place.value = _place.value?.copy(description = description)
    }

    fun saveChanges() {
        viewModelScope.launch {
            val currentPlace = _place.value
            if (currentPlace != null) {
                firebaseService.updatePlace(placeId, currentPlace.name, currentPlace.description)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ai::class, ExperimentalMaterial3Api::class)
@Composable
fun EditPlaceScreen(navController: NavController, viewModel: EditPlaceViewModel = viewModel()) {
    val place by viewModel.place.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit ${place?.name ?: "..."}") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        place?.let {
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                OutlinedTextField(
                    value = it.name,
                    onValueChange = viewModel::onNameChange,
                    label = { Text("Place Name") }
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = it.description,
                    onValueChange = viewModel::onDescriptionChange,
                    label = { Text("Description") }
                )
                Spacer(Modifier.height(24.dp))
                Button(onClick = {
                    viewModel.saveChanges()
                    navController.popBackStack()
                }) {
                    Text("Save Changes")
                }
            }
        }
    }
}
