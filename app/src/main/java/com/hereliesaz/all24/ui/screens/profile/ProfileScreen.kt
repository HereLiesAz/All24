package com.hereliesaz.all24.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    navController: NavController,
    onSignOut: () -> Unit
) {
    val currentUser = FirebaseAuth.getInstance().currentUser

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (currentUser != null) {
            Text(text = "Profile Screen")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = currentUser.displayName ?: "No Name")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                FirebaseAuth.getInstance().signOut()
                onSignOut()
            }) {
                Text(text = "Sign Out")
            }
        }
    }
}
