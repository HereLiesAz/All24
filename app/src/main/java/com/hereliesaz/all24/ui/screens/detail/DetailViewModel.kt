package com.hereliesaz.all24.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _comment1 = MutableStateFlow("")
    val comment1 = _comment1.asStateFlow()

    private val _comment2 = MutableStateFlow("")
    val comment2 = _comment2.asStateFlow()

    private val _comment3 = MutableStateFlow("")
    val comment3 = _comment3.asStateFlow()

    fun onComment1Changed(newComment: String) {
        _comment1.value = newComment
    }

    fun onComment2Changed(newComment: String) {
        _comment2.value = newComment
    }

    fun onComment3Changed(newComment: String) {
        _comment3.value = newComment
    }

    fun submitComments() {
        viewModelScope.launch {
            // TODO: Implement actual submission logic (e.g., API call)
            println("Submitting comments: ${comment1.value}, ${comment2.value}, ${comment3.value}")
        }
    }
}
