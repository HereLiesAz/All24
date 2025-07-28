package com.hereliesaz.all24.ui.screens.carousel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainCarouselUiState(
    val categories: List<String> = emptyList(),
    val placesByCategories: Map<String, List<Place>> = emptyMap(),
    val isLoading: Boolean = true,
)

class MainCarouselViewModel : ViewModel() {

    private val sheetsService = SheetsService()
    private val _uiState = MutableStateFlow(MainCarouselUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadContent()
    }

    private fun loadContent() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val places = sheetsService.getPlaces()
            val categories =
                listOf("Happy Hours", "24-Hour Bars", "Late-Night Food", "Pop-ups", "Events")
            val placesByCategories = categories.associateWith { category ->
                places.filter { it.tags.contains(category) }
            }

            _uiState.update {
                it.copy(
                    isLoading = false,
                    categories = categories,
                    placesByCategories = placesByCategories
                )
            }
        }
    }
}