package com.hereliesaz.all24.ui.auth

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    navController: NavController,
    googleSignInClient: GoogleSignInClient,
    authViewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()

    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                authViewModel.signInWithGoogle(account)
            }
        } catch (e: ApiException) {
            e.printStackTrace()
        }
    }

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Become Corporeal") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (authState) {
                is AuthState.Loading -> {
                    CircularProgressIndicator()
                }
                is AuthState.Error -> {
                    Text(text = (authState as AuthState.Error).message, color = MaterialTheme.colorScheme.error)
                }
                else -> {
                    Text("To leave your mark, you must have a soul.")
                    Spacer(Modifier.height(24.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { /* TODO: Implement email/password sign in */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Sign In")
                    }
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = { /* TODO: Implement create account */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Create Account")
                    }

                    Spacer(Modifier.height(24.dp))

                    Text("or")

                    Spacer(Modifier.height(24.dp))

                    Button(onClick = { signInLauncher.launch(googleSignInClient.signInIntent) }) {
                        Text("Sign in with Google")
                    }
                }
            }
        }
    }
}