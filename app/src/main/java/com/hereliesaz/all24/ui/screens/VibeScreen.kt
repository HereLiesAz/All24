package com.hereliesaz.all24.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.engage.social.datamodel.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.ui.navigation.Screen
import kotlin.random.Random

@Composable
fun VibeScreen(navController: NavController, viewModel: VibeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        ParticleCanvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { viewModel.conjureRecommendations() })
            })

        // Animated recommendations list would go here...

        // Navigation Buttons
        VibeNavButton(
            icon = Icons.Default.Person,
            tooltip = "Profile",
            alignment = Alignment.TopStart,
            onClick = { navController.navigate(Screen.Profile.route) }
        )
        // Add other buttons...
    }
}

@Composable
private fun ParticleCanvas(modifier: Modifier = Modifier) {
    // This is a simplified version of the particle effect.
    // A more robust implementation would manage particle state in a ViewModel.
    val particles = remember {
        List(100) {
            Particle(
                position = Offset(Random.nextFloat(), Random.nextFloat()),
                velocity = Offset(Random.nextFloat() * 0.001f - 0.0005f, Random.nextFloat() * 0.001f - 0.0005f),
                radius = Random.nextFloat() * 2f + 1f,
                alpha = Random.nextFloat() * 0.5f
            )
        }
    }
    val animatable = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatable.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing)
            )
        )
    }

    Canvas(modifier = modifier.background(Color.Black)) {
        val time = animatable.value // This forces recomposition every frame
        particles.forEach { particle ->
            particle.update()
            drawCircle(
                color = Color.White.copy(alpha = particle.alpha),
                center = Offset(particle.position.x * size.width, particle.position.y * size.height),
                radius = particle.radius
            )
        }
    }
}

private data class Particle(
    var position: Offset,
    val velocity: Offset,
    val radius: Float,
    val alpha: Float
) {
    fun update() {
        position = position.plus(velocity)
        // Wrap around screen
        if (position.x < 0) position = position.copy(x = 1f)
        if (position.x > 1) position = position.copy(x = 0f)
        if (position.y < 0) position = position.copy(y = 1f)
        if (position.y > 1) position = position.copy(y = 0f)
    }
}

@Composable
fun BoxScope.VibeNavButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    tooltip: String,
    alignment: Alignment,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .align(alignment)
            .padding(24.dp)
    ) {
        Icon(icon, contentDescription = tooltip, tint = Color.White)
    }
}

// Placeholder for protected action check
fun onProtectedAction(navController: NavController, action: () -> Unit) {
    val user = Firebase.auth.currentUser
    if (user == null || user.isAnonymous) {
        navController.navigate(Screen.Auth.route)
    } else {
        action()
    }
}
