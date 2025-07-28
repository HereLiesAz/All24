package com.hereliesaz.all24.ui.screens.add.review

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
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

    fun submitReview(placeId: String, context: Context) {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        if (account?.idToken == null) {
            _uiState.value = _uiState.value.copy(error = "You must be signed in to leave a review.")
            return
        }
        val idToken = account.idToken!!

        if (_uiState.value.text.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Review cannot be empty.")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                sheetsService.addReview(
                    placeId = placeId,
                    text = _uiState.value.text,
                    vote = _uiState.value.vote,
                    idToken = idToken
                )
                _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}