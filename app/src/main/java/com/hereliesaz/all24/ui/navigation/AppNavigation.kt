package com.hereliesaz.all24.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hereliesaz.all24.ui.screens.auth.AuthScreen
import com.hereliesaz.all24.ui.screens.detail.DetailScreen
import com.hereliesaz.all24.ui.screens.home.HomeScreen
import com.hereliesaz.all24.ui.screens.profile.ProfileScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
    object Auth : Screen("auth")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            DetailScreen(itemId = backStackEntry.arguments?.getInt("itemId"))
        }
        composable(Screen.Auth.route) {
            AuthScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
