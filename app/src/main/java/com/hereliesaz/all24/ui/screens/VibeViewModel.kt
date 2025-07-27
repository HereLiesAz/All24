package com.hereliesaz.all24.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.min
import kotlin.random.Random

data class VibeUiState(
    val recommendations: List<Pair<Place, Review?>> = emptyList(),
    val showRecommendations: Boolean = false,
    val error: String? = null,
)

class VibeViewModel : ViewModel() {

    private val sheetsService = SheetsService()

    private val _uiState = MutableStateFlow(VibeUiState())
    val uiState = _uiState.asStateFlow()

    fun conjureRecommendations() {
        viewModelScope.launch {
            try {
                val places = sheetsService.getPlaces()
                val allReviews = sheetsService.getAllReviews()

                if (places.isNotEmpty()) {
                    val recommendations = getWeightedRandomPlaces(places, allReviews)
                    _uiState.value = _uiState.value.copy(
                        recommendations = recommendations,
                        showRecommendations = true,
                        error = null
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value =
                    _uiState.value.copy(error = "The spirits (or Google's API) are not responding.")
            }
        }
    }

    // This logic remains, a ghost of the old machine's intelligence.
    private fun getWeightedRandomPlaces(
        places: List<Place>,
        reviews: List<Review>,
    ): List<Pair<Place, Review?>> {
        val reviewsByPlaceId = reviews.groupBy { it.placeId }
        val weightedPlaces = mutableListOf<Triple<Place, Double, Review?>>()

        for (place in places) {
            val placeReviews = reviewsByPlaceId[place.id] ?: emptyList()
            var vibeScore = 0.0
            var mostInfluentialReview: Review? = null
            var maxReviewScore = -1.0
            val now = Date().time

            for (review in placeReviews) {
                val reviewTimestamp = review.timestamp?.time ?: now
                val ageInMillis = now - reviewTimestamp
                val ageInDays = TimeUnit.MILLISECONDS.toDays(ageInMillis).toDouble()
                val individualScore =
                    (review.endorsedBy.size - review.avoidedBy.size) / (ageInDays + 2.0)

                if (individualScore > maxReviewScore) {
                    maxReviewScore = individualScore
                    mostInfluentialReview = review
                }
                vibeScore += individualScore
            }

            if (vibeScore > 0) {
                weightedPlaces.add(Triple(place, vibeScore, mostInfluentialReview))
            }
        }

        return if (weightedPlaces.isEmpty()) {
            places.shuffled().take(min(3, places.size)).map { Pair(it, null) }
        } else {
            performWeightedSelection(weightedPlaces, 3)
                .map { Pair(it.first, it.third) }
        }
    }

    private fun performWeightedSelection(
        weightedPlaces: MutableList<Triple<Place, Double, Review?>>,
        count: Int,
    ): List<Triple<Place, Double, Review?>> {
        val recommendations = mutableListOf<Triple<Place, Double, Review?>>()
        var totalWeight = weightedPlaces.sumOf { it.second }
        val selectionCount = min(count, weightedPlaces.size)

        repeat(selectionCount) {
            if (totalWeight <= 0) return@repeat
            val randomValue = Random.nextDouble() * totalWeight
            var currentWeight = 0.0
            var selectedIndex = -1

            for ((index, triple) in weightedPlaces.withIndex()) {
                currentWeight += triple.second
                if (randomValue < currentWeight) {
                    selectedIndex = index
                    break
                }
            }

            if (selectedIndex != -1) {
                val selectedTriple = weightedPlaces.removeAt(selectedIndex)
                recommendations.add(selectedTriple)
                totalWeight -= selectedTriple.second
            }
        }
        return recommendations
    }


    fun hideRecommendations() {
        _uiState.value = _uiState.value.copy(showRecommendations = false)
    }
}