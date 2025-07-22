package com.hereliesaz.all24.ui.screens.submit_place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.services.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SubmitPlaceUiState(
    val name: String = "",
    val description: String = "",
    val address: String = "",
    val category: String = "bar",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class SubmitPlaceViewModel : ViewModel() {
    private val firebaseService = FirebaseService()

    private val _uiState = MutableStateFlow(SubmitPlaceUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun onDescriptionChange(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun onAddressChange(address: String) {
        _uiState.value = _uiState.value.copy(address = address)
    }

    fun onCategoryChange(category: String) {
        _uiState.value = _uiState.value.copy(category = category)
    }

    fun submit() {
        if (_uiState.value.name.isBlank() || _uiState.value.description.isBlank() || _uiState.value.address.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "All fields are required.")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                firebaseService.submitPlace(
                    name = _uiState.value.name,
                    description = _uiState.value.description,
                    address = _uiState.value.address,
                    category = _uiState.value.category
                )
                _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}
