package com.hereliesaz.all24.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hereliesaz.all24.services.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for handling the business logic of the authentication screen.
 */
class AuthViewModel : ViewModel() {

    private val firebaseService = FirebaseService()

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun submit() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                if (_uiState.value.isLoginMode) {
                    firebaseService.signInWithEmail(_uiState.value.email, _uiState.value.password)
                } else {
                    firebaseService.signUpWithEmail(_uiState.value.email, _uiState.value.password)
                }
                _uiState.value = _uiState.value.copy(isLoading = false, isAuthenticated = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun toggleMode() {
        _uiState.value = _uiState.value.copy(isLoginMode = !_uiState.value.isLoginMode)
    }
}
