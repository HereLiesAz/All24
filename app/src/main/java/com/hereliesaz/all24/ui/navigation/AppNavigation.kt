package com.hereliesaz.all24.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hereliesaz.all24.ui.screens.auth.AuthScreen
import com.hereliesaz.all24.ui.screens.detail.DetailScreen
import com.hereliesaz.all24.ui.screens.home.HomeScreen
import com.hereliesaz.all24.ui.screens.map.MapScreen
import com.hereliesaz.all24.ui.screens.profile.ProfileScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
    object Map : Screen("map")
    object Auth : Screen("auth")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = currentDestination?.hierarchy?.any { it.route == Screen.Home.route } == true,
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.LocationOn, contentDescription = "Map") },
                    label = { Text("Map") },
                    selected = currentDestination?.hierarchy?.any { it.route == Screen.Map.route } == true,
                    onClick = {
                        navController.navigate(Screen.Map.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = currentDestination?.hierarchy?.any { it.route == Screen.Profile.route } == true,
                    onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
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
                AuthScreen(
                    onSignInSuccess = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.Auth.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navController = navController,
                    onSignOut = {
                        navController.navigate(Screen.Auth.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Map.route) {
                MapScreen()
            }
        }
    }
}
