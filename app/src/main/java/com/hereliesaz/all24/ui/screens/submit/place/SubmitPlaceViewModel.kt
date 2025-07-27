package com.hereliesaz.all24.ui.screens.submit.place

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.hereliesaz.all24.services.SheetsService
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
    private val sheetsService = SheetsService()

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

    fun submit(context: Context) {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        if (account == null || account.idToken == null) {
            _uiState.value =
                _uiState.value.copy(error = "Authentication error. Please sign in again.")
            return
        }
        val idToken = account.idToken!!

        if (_uiState.value.name.isBlank() || _uiState.value.description.isBlank() || _uiState.value.address.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "All fields are required.")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                sheetsService.submitPlace(
                    idToken = idToken,
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