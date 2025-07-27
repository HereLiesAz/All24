package com.hereliesaz.all24.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.ui.navigation.Screen
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun VibeScreen(navController: NavController, viewModel: VibeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var touchInteraction by remember { mutableStateOf<TouchInteraction>(TouchInteraction.Idle) }

    LaunchedEffect(touchInteraction) {
        if (touchInteraction is TouchInteraction.Exploding) {
            delay(2000L)
            if (touchInteraction is TouchInteraction.Exploding) {
                touchInteraction = TouchInteraction.Idle
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ParticleCanvas(
            touchInteraction = touchInteraction,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            val isPressed = event.changes.any { it.pressed }

                            if (isPressed) {
                                // Convert 2D screen touch to 3D world coordinates
                                val touchPos = event.changes.first().position
                                val x = (touchPos.x / size.width - 0.5f) * 2f
                                val y = (touchPos.y / size.height - 0.5f) * 2f
                                touchInteraction = TouchInteraction.Attracting(Vector3(x, y, 0f))
                            } else {
                                if (touchInteraction is TouchInteraction.Attracting) {
                                    val lastPosition =
                                        (touchInteraction as TouchInteraction.Attracting).position
                                    touchInteraction =
                                        TouchInteraction.Exploding(from = lastPosition)
                                    viewModel.conjureRecommendations()
                                }
                            }
                        }
                    }
                }
        )

        // The recommendations now appear in the center of the screen
        AnimatedVisibility(
            visible = uiState.showRecommendations,
            modifier = Modifier.align(Alignment.Center),
            enter = fadeIn(animationSpec = tween(delayMillis = 400, durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(uiState.recommendations) { index, (place, review) ->
                    RecommendationCard(place = place, review = review)
                    if (index < uiState.recommendations.size - 1) {
                        ParticleDivider()
                    }
                }
            }
        }

        VibeNavButton(
            icon = Icons.Default.Person,
            tooltip = "Profile",
            alignment = Alignment.TopStart,
            onClick = { navController.navigate(Screen.Profile.route) }
        )
    }
}

@Composable
private fun RecommendationCard(place: Place, review: Review?) {
    // Restyled to match the reference image: no Card, just a Column with a transparent background
    Column(
        modifier = Modifier
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(vertical = 12.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = place.name,
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "•",
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
        review?.let {
            Spacer(Modifier.height(2.dp))
            Text(
                text = it.text,
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ParticleDivider() {
    val particles = remember {
        List(100) {
            Offset(
                x = Random.nextFloat(),
                y = Random.nextFloat() * 0.5f + 0.25f // Distribute vertically in middle
            ) to Random.nextFloat() * 0.5f + 0.2f // Alpha
        }
    }
    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(24.dp)
    ) {
        particles.forEach { (offset, alpha) ->
            drawCircle(
                color = Color.White,
                radius = 0.7f,
                alpha = alpha,
                center = Offset(offset.x * size.width, offset.y * size.height)
            )
        }
    }
}

@Composable
private fun ParticleCanvas(touchInteraction: TouchInteraction, modifier: Modifier = Modifier) {
    val particles = remember {
        val radius = 2.5f // Radius of the particle disk in 3D space
        List(4500) {
            val angle = Random.nextFloat() * 2f * Math.PI.toFloat()
            val r = sqrt(Random.nextFloat()) * radius
            val x = cos(angle) * r
            val y = sin(angle) * r

            Particle(
                initialPosition = Vector3(x, y, 5f + Random.nextFloat() * 2f),
                initialVelocity = Vector3(0f, 0f, -0.005f) // Slow drift towards the camera
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

    val bokehBrush = Brush.radialGradient(
        colors = listOf(Color.White.copy(alpha = 0.5f), Color.Transparent),
    )

    Canvas(modifier = modifier.background(Color.Black)) {
        val time = animatable.value // Forces recomposition every frame
        val centerX = size.width / 2
        val centerY = size.height / 2
        val fieldOfView = size.width * 0.8f

        val sortedParticles = particles.sortedByDescending { it.position.z }

        sortedParticles.forEach { particle ->
            particle.update(touchInteraction, Vector3(2.5f, 2.5f, 7f))
            // Project 3D position to 2D screen space
            val scale = fieldOfView / (fieldOfView + particle.position.z)
            if (scale > 0) {
                val screenX = particle.position.x * scale + centerX
                val screenY = particle.position.y * scale + centerY
                val screenRadius = scale * 2.5f

                val alpha = ((7f - particle.position.z) / 7f).coerceIn(0f, 1f) * particle.life

                drawCircle(
                    brush = bokehBrush,
                    center = Offset(screenX, screenY),
                    radius = screenRadius,
                    alpha = alpha
                )
                drawCircle(
                    color = Color.White,
                    radius = screenRadius * 0.2f,
                    center = Offset(screenX, screenY),
                    alpha = alpha
                )
            }
        }
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

@Composable
fun onProtectedAction(navController: NavController, action: () -> Unit) {
    val context = LocalContext.current
    val account = GoogleSignIn.getLastSignedInAccount(context)
    if (account == null) {
        navController.navigate(Screen.Auth.route)
    } else {
        action()
    }
}