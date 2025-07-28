package com.hereliesaz.all24.ui.screens.place.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PlaceDetailUiState(
    val place: Place? = null,
    val reviews: List<Review> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
)

class PlaceDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val placeId: String = checkNotNull(savedStateHandle["placeId"])
    private val sheetsService = SheetsService()

    private val _uiState = MutableStateFlow(PlaceDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                // In a Sheets-based world, we fetch everything at once.
                val allPlaces = sheetsService.getPlaces()
                val allReviews = sheetsService.getAllReviews()

                val place = allPlaces.find { it.id == placeId }
                if (place != null) {
                    val reviews = allReviews.filter { it.placeId == placeId }

                    _uiState.value = _uiState.value.copy(
                        place = place,
                        reviews = reviews,
                        isLoading = false
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Place not found."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    // Add functions for verifying reviews, etc., which would call the SheetsService proxy.
}