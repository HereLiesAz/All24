package com.hereliesaz.all24.ui.screens.business

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditPlaceViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val sheetsService = SheetsService()
    private val placeId: String = savedStateHandle.get<String>("placeId")!!

    private val _place = MutableStateFlow<Place?>(null)
    val place = _place.asStateFlow()

    init {
        viewModelScope.launch {
            // This logic needs to be adapted for SheetsService
            // _place.value = sheetsService.getPlaceById(placeId)
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
                // This would now call a proxy function in SheetsService
                // sheetsService.updatePlace(placeId, currentPlace.name, currentPlace.description)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
            Column(modifier = Modifier
                .padding(padding)
                .padding(16.dp)) {
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