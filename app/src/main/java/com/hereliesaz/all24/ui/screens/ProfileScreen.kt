package com.hereliesaz.all24.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hereliesaz.all24.services.FirebaseService
import com.hereliesaz.all24.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController) {
    val user = Firebase.auth.currentUser
    val scope = rememberCoroutineScope()
    val firebaseService = FirebaseService()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (user != null && !user.isAnonymous) {
                // Authenticated User View
                Text("Signed in as:")
                Spacer(Modifier.height(8.dp))
                Text(user.email ?: "N/A", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = {
                        scope.launch {
                            firebaseService.signOut()
                            // Navigate back to Vibe, which will be the new 'home'
                            navController.navigate(Screen.Vibe.route) {
                                popUpTo(Screen.Vibe.route) { inclusive = true }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Sign Out")
                }
            } else {
                // Anonymous User View
                Text("You are browsing anonymously.")
                Spacer(Modifier.height(16.dp))
                Button(onClick = { navController.navigate(Screen.Auth.route) }) {
                    Text("Login / Sign Up")
                }
            }
        }
    }
}
