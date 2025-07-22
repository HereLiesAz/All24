package com.hereliesaz.all24.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.services.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.min

data class VibeUiState(
    val recommendations: List<Place> = emptyList(),
    val showRecommendations: Boolean = false
)

class VibeViewModel : ViewModel() {

    private val firebaseService = FirebaseService()

    private val _uiState = MutableStateFlow(VibeUiState())
    val uiState = _uiState.asStateFlow()

    fun conjureRecommendations() {
        viewModelScope.launch {
            try {
                val places = firebaseService.getPlaces()
                if (places.isNotEmpty()) {
                    val shuffled = places.shuffled()
                    val count = min(3, places.size)
                    _uiState.value = _uiState.value.copy(
                        recommendations = shuffled.take(count),
                        showRecommendations = true
                    )
                }
            } catch (e: Exception) {
                // Handle error
                println("Failed to conjure recommendations: ${e.message}")
            }
        }
    }

    fun hideRecommendations() {
        _uiState.value = _uiState.value.copy(showRecommendations = false)
    }
}
