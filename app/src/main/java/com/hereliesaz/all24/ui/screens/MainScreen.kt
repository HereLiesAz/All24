package com.hereliesaz.all24.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hereliesaz.all24.ui.navigation.AppNavigation
import com.hereliesaz.all24.ui.navigation.Screen

import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun MainScreen(googleSignInClient: GoogleSignInClient) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    Screen.PlacesList,
                    Screen.Map,
                )
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                Screen.PlacesList -> Icon(Icons.Filled.Home, contentDescription = null)
                                Screen.Map -> Icon(Icons.Filled.LocationOn, contentDescription = null)
                                else -> {}
                            }
                        },
                        label = { Text(screen.route) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
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
        }
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            googleSignInClient = googleSignInClient
        )
    }
}
