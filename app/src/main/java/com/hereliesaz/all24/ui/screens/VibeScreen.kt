package com.hereliesaz.all24.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.prathamesh.jetpack_compose_parabolic_animation.CreateParticles
import com.prathamesh.jetpack_compose_parabolic_animation.properties.EmissionType
import com.prathamesh.jetpack_compose_parabolic_animation.properties.Force
import com.prathamesh.jetpack_compose_parabolic_animation.properties.LifeTime
import com.prathamesh.jetpack_compose_parabolic_animation.properties.ParticleColor
import com.prathamesh.jetpack_compose_parabolic_animation.properties.ParticleSize
import com.prathamesh.jetpack_compose_parabolic_animation.properties.Velocity

@Composable
fun VibeScreen(navController: NavController, viewModel: VibeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    if (uiState.showRecommendations) {
                        viewModel.hideRecommendations()
                    } else {
                        viewModel.conjureRecommendations()
                    }
                })
            }
    ) {
        BoxWithConstraints {
            // This library creates particles that animate from a single point.
            // To create a full-screen "waterfall", we can create many emitters across the top.
            val emitterWidth = constraints.maxWidth / 10
            for (i in 0..10) {
                CreateParticles(
                    modifier = Modifier.fillMaxSize(),
                    x = (i * emitterWidth).toFloat(),
                    y = -50f, // Start above the screen
                    velocity = Velocity(
                        xDirection = 0f,
                        yDirection = 15f,
                        angle = 90.0,
                        randomize = true
                    ),
                    force = Force.Gravity(0.1f),
                    particleSize = ParticleSize.RandomSizes(2..8),
                    particleColor = ParticleColor.SingleColor(Color.White.copy(alpha = 0.8f)),
                    lifeTime = LifeTime(255f, 0.05f),
                    emissionType = EmissionType.FlowEmission(
                        maxParticles = 500,
                        emissionRate = 0.5f
                    ),
                )
            }
        }

        // The recommendations now appear in the center of the screen
        AnimatedVisibility(
            visible = uiState.showRecommendations,
            modifier = Modifier.align(Alignment.Center),
            enter = fadeIn(animationSpec = tween(delayMillis = 400, durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(Color.Black.copy(alpha = 0.7f)),
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
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp),
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
    // A simple divider is more suitable now
    Spacer(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(24.dp)
    )
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