package com.hereliesaz.all24.ui.screens.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Submission
import com.hereliesaz.all24.services.SheetsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdminDashboardViewModel : ViewModel() {

    private val sheetsService = SheetsService()

    private val _submissions = MutableStateFlow<List<Submission>>(emptyList())
    val submissions: StateFlow<List<Submission>> = _submissions.asStateFlow()

    init {
        fetchSubmissions()
    }

    fun fetchSubmissions() {
        viewModelScope.launch {
            try {
                _submissions.value = sheetsService.getSubmissions()
            } catch (e: Exception) {
                // Handle error
                println("Failed to fetch submissions: ${e.message}")
            }
        }
    }
}