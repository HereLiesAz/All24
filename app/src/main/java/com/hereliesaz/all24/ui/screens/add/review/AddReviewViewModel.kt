package com.hereliesaz.all24.ui.screens.add.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AddReviewUiState(
    val text: String = "",
    val vote: String = "endorse",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class AddReviewViewModel : ViewModel() {
    private val sheetsService = SheetsService()

    private val _uiState = MutableStateFlow(AddReviewUiState())
    val uiState = _uiState.asStateFlow()

    fun onTextChange(text: String) {
        _uiState.value = _uiState.value.copy(text = text)
    }

    fun onVoteChange(vote: String) {
        _uiState.value = _uiState.value.copy(vote = vote)
    }

    fun submitReview(placeId: String) {
        if (_uiState.value.text.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Review cannot be empty.")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                // This now needs to be a proxied call in SheetsService
                // sheetsService.addReview(placeId, _uiState.value.text, _uiState.value.vote)
                _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}