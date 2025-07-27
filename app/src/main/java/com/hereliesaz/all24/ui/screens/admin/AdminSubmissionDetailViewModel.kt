package com.hereliesaz.all24.ui.screens.admin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Submission
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AdminSubmissionDetailState(
    val submission: Submission? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)

class AdminSubmissionDetailViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
) : AndroidViewModel(application) {

    private val sheetsService = SheetsService()
    private val submissionId: String = savedStateHandle.get<String>("submissionId")!!
    private val _uiState = MutableStateFlow(AdminSubmissionDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                // val submission = sheetsService.getSubmissionById(submissionId)
                // _uiState.value = _uiState.value.copy(submission = submission, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    fun approve() {
        viewModelScope.launch {
            _uiState.value.submission?.let {
                try {
                    // sheetsService.approveSubmission(it, getApplication())
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(error = "Action failed: ${e.message}")
                }
            }
        }
    }

    fun deny() {
        viewModelScope.launch {
            try {
                // sheetsService.denySubmission(submissionId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}