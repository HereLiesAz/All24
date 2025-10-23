package com.hereliesaz.all24.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.hereliesaz.all24.ui.auth.AuthScreen
import com.hereliesaz.all24.ui.screens.MainScreen
import com.hereliesaz.all24.ui.screens.onboarding.OnboardingScreen
import com.hereliesaz.all24.ui.screens.detail.DetailScreen


sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Main : Screen("main")
    object Home : Screen("home")
    object Map : Screen("map")
    object Profile : Screen("profile")
    object Auth : Screen("auth")
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(googleSignInClient: GoogleSignInClient) {
    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = Screen.Onboarding.route) {

            composable(Screen.Onboarding.route) {
                OnboardingScreen(navController = navController)
            }

            composable(Screen.Main.route) {
                MainScreen()
            }

            composable(Screen.Auth.route) {
                AuthScreen(
                    navController = navController,
                    googleSignInClient = googleSignInClient
                )
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                DetailScreen(
                    itemId = backStackEntry.arguments?.getInt("itemId"),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }
        }
    }
}