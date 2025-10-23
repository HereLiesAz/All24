package com.hereliesaz.all24.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hereliesaz.all24.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    googleSignInClient: GoogleSignInClient,
) {
    val currentUser = Firebase.auth.currentUser

    val signOut = {
        Firebase.auth.signOut()
        googleSignInClient.signOut().addOnCompleteListener {
            navController.navigate(Screen.Profile.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Your State of Being") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (currentUser != null) {
                Text("Signed in as:")
                Spacer(Modifier.height(8.dp))
                Text(currentUser.displayName ?: "N/A", style = MaterialTheme.typography.titleLarge)
                Text(currentUser.email ?: "N/A", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(32.dp))

                Text("Vouched Places", style = MaterialTheme.typography.titleLarge)
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    modifier = Modifier.height(200.dp) // Constrain height for the example
                ) {
                    // Placeholder data
                    items(10) { index ->
                        Card(modifier = Modifier.padding(4.dp)) {
                            Text("Vouched Place ${index + 1}", modifier = Modifier.padding(8.dp))
                        }
                    }
                }

                Spacer(Modifier.height(32.dp))

                Text("Tastemaker Comments", style = MaterialTheme.typography.titleLarge)
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    modifier = Modifier.height(200.dp) // Constrain height for the example
                ) {
                    // Placeholder data
                    items(3) { index ->
                        Card(modifier = Modifier.padding(4.dp)) {
                            Text("Tastemaker Comment ${index + 1}", modifier = Modifier.padding(8.dp))
                        }
                    }
                }


                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = { signOut() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Return to Ghost")
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("You are a ghost, a silent observer.")
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = { navController.navigate(Screen.Auth.route) }) {
                        Text("Login / Sign Up")
                    }
                }
            }
        }
    }
}