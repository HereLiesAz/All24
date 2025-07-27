package com.hereliesaz.all24.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.hereliesaz.all24.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    googleSignInClient: GoogleSignInClient,
) {
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(LocalContext.current)

    val signOut = {
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (account != null) {
                Text("Signed in as:")
                Spacer(Modifier.height(8.dp))
                Text(account.displayName ?: "N/A", style = MaterialTheme.typography.titleLarge)
                Text(account.email ?: "N/A", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = { signOut() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Return to Ghost")
                }
            } else {
                Text("You are a ghost, a silent observer.")
                Spacer(Modifier.height(16.dp))
                Button(onClick = { navController.navigate(Screen.Auth.route) }) {
                    Text("Login / Sign Up")
                }
            }
        }
    }
}