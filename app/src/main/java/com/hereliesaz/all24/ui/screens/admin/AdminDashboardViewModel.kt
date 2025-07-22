package com.hereliesaz.all24.ui.screens.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.data.Submission
import com.hereliesaz.all24.services.FirebaseService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AdminDashboardViewModel : ViewModel() {

    private val firebaseService = FirebaseService()

    val submissions: StateFlow<List<Submission>> = firebaseService.getSubmissionsStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // In a real app, approve/deny logic would be here
    // fun approve(submission: Submission) { ... }
    // fun deny(submissionId: String) { ... }
}
