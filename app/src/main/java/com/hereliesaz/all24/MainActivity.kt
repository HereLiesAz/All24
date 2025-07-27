package com.hereliesaz.all24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.hereliesaz.all24.ui.navigation.AppNavigation
import com.hereliesaz.all24.ui.theme.All24Theme

class MainActivity : ComponentActivity() {

    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("944329350333-lmqcgnhpsi1cd0noj0o4nf9ejaj707u6.apps.googleusercontent.com") // You get this from your Google Cloud console
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)


        setContent {
            All24Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Pass the client to the navigation host
                    AppNavigation(googleSignInClient)
                }
            }
        }
    }
}