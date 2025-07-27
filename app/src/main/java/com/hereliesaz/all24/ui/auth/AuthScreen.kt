package com.hereliesaz.all24.ui.auth

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException

@Composable
fun AuthScreen(
    navController: NavController,
    googleSignInClient: GoogleSignInClient,
) {
    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            // Successful sign-in. The account is now globally available via GoogleSignIn.getLastSignedInAccount().
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                // The ritual is complete. Pop back to the previous screen.
                navController.popBackStack()
            }
        } catch (e: ApiException) {
            // The spirits are displeased. Or the network is down.
            e.printStackTrace()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Become Corporeal") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("To leave your mark, you must have a soul.")
            Spacer(Modifier.height(24.dp))
            Button(onClick = { signInLauncher.launch(googleSignInClient.signInIntent) }) {
                Text("Sign in with Google")
            }
        }
    }
}