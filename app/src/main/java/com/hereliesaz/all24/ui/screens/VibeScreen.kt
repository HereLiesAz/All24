package com.hereliesaz.all24.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
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

        AnimatedVisibility(
            visible = uiState.showRecommendations,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut()
        ) {
            // Fading edge at the bottom of the screen
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black),
                            startY = 0f,
                            endY = 400f
                        )
                    )
                    .padding(top = 100.dp)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.heightIn(max = 400.dp)
                ) {
                    items(uiState.recommendations) { (place, review) ->
                        RecommendationCard(place = place, review = review)
                    }
                }
            }
        }


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
private fun RecommendationCard(place: Place, review: Review?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = place.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            review?.let {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "\"${it.text}\"",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
private fun ParticleCanvas(modifier: Modifier = Modifier) {
    // A series of points that vaguely resemble the layout of New Orleans from above.
    val newOrleansMapPoints = remember {
        listOf(
            // Mississippi River Crescent
            Offset(0.2f, 0.9f), Offset(0.25f, 0.85f), Offset(0.3f, 0.8f),
            Offset(0.4f, 0.75f), Offset(0.5f, 0.72f), Offset(0.6f, 0.75f),
            Offset(0.7f, 0.8f), Offset(0.75f, 0.85f), Offset(0.8f, 0.9f),

            // French Quarter / CBD / Warehouse District Cluster
            Offset(0.55f, 0.68f), Offset(0.58f, 0.65f), Offset(0.6f, 0.62f),
            Offset(0.63f, 0.64f), Offset(0.57f, 0.61f), Offset(0.61f, 0.58f),
            Offset(0.65f, 0.6f),

            // Uptown / Garden District Trace
            Offset(0.4f, 0.68f), Offset(0.35f, 0.72f), Offset(0.3f, 0.77f),
            Offset(0.25f, 0.8f),

            // Mid-City / Major Thoroughfares
            Offset(0.5f, 0.5f), Offset(0.6f, 0.45f), Offset(0.7f, 0.35f),
            Offset(0.45f, 0.4f), Offset(0.55f, 0.3f), Offset(0.35f, 0.55f),
            Offset(0.4f, 0.25f)
        )
    }

    val particles = remember {
        List(250) { // Increased particle count for a denser "city lights" feel
            val basePoint = newOrleansMapPoints.random()
            // Add a small, random "jitter" to each point to create a clustered, glowing effect
            val jitterX = (Random.nextFloat() - 0.5f) * 0.08f
            val jitterY = (Random.nextFloat() - 0.5f) * 0.08f

            Particle(
                position = Offset(basePoint.x + jitterX, basePoint.y + jitterY),
                // Slower, more subtle movement to mimic twinkling
                velocity = Offset(
                    Random.nextFloat() * 0.0005f - 0.00025f,
                    Random.nextFloat() * 0.0005f - 0.00025f
                ),
                radius = Random.nextFloat() * 1.5f + 0.5f,
                alpha = Random.nextFloat() * 0.7f + 0.2f
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