package com.hereliesaz.all24.ui.screens.top.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopReviewsViewModel : ViewModel() {

    private val sheetsService = SheetsService()

    private val _adminReviews = MutableStateFlow<List<Review>>(emptyList())
    val adminReviews: StateFlow<List<Review>> = _adminReviews.asStateFlow()

    init {
        fetchAdminReviews()
    }

    private fun fetchAdminReviews() {
        viewModelScope.launch {
            // _adminReviews.value = sheetsService.getAdminReviews()
        }
    }

    suspend fun getPlaceForReview(review: Review): Place? {
        // return sheetsService.getPlaceById(review.placeId)
        return null
    }
}