package com.hereliesaz.all24.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hereliesaz.all24.ui.auth.AuthScreen
import com.hereliesaz.all24.ui.auth.AuthViewModel
import com.hereliesaz.all24.ui.screens.VibeScreen

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Vibe : Screen("vibe")
    // Add other screens here: Profile, TopReviews, SubmitPlace, AdminDashboard, etc.
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route // Always start at Auth to check state
    ) {
        composable(Screen.Auth.route) {
            AuthScreen(
                onNavigateToVibe = {
                    navController.navigate(Screen.Vibe.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Vibe.route) {
            VibeScreen()
        }
        // Define other routes here
    }
}
