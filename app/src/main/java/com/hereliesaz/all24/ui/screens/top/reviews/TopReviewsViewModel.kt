package com.hereliesaz.all24.ui.screens.top.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.services.FirebaseService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TopReviewsViewModel : ViewModel() {

    private val firebaseService = FirebaseService()

    val adminReviews: StateFlow<List<Review>> = firebaseService.getAdminReviewsStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    suspend fun getPlaceForReview(review: Review): Place? {
        return firebaseService.getPlaceById(review.placeId)
    }
}