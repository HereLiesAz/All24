package com.hereliesaz.all24.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hereliesaz.all24.ui.theme.All24Theme

@Composable
fun AuthScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /* TODO */ }) {
            Text("Sign in with Google")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    All24Theme {
        AuthScreen()
    }
}
