package com.hereliesaz.all24.ui.auth

/**
 * Represents the state of the authentication screen.
 */
data class AuthUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isLoginMode: Boolean = true,
    val error: String? = null,
    val isAuthenticated: Boolean = false
)
