package com.hereliesaz.all24.ui.screens

import kotlin.random.Random

/**
 * Represents the state of the user's touch interaction on the VibeScreen
 * canvas.
 */
sealed class TouchInteraction {
    object Idle : TouchInteraction()
    data class Attracting(val position: Vector3) : TouchInteraction()
    data class Exploding(val from: Vector3) : TouchInteraction()
}

/**
 * Represents a single particle in the VibeScreen simulation. Operates in a
 * 3D space and is projected onto the 2D canvas.
 */
class Particle(
    private val initialPosition: Vector3,
    private val initialVelocity: Vector3,
) {
    var position: Vector3 = initialPosition.copy()
    var velocity: Vector3 = initialVelocity.copy()

    var life: Float = 1.0f

    // --- PHYSICS CONSTANTS ---
    private val ATTRACTION_RADIUS = 1.2f
    private val FRENZY_STRENGTH = 0.002f
    private val ATTRACTION_STRENGTH = 0.03f
    private val SWIRL_STRENGTH = 0.025f
    private val TORNADO_LIFT_STRENGTH = 0.04f // Pulls particles towards the camera (Z axis)
    private val DISSIPATION_STRENGTH = 0.1f
    private val FRICTION = 0.92f

    fun update(interaction: TouchInteraction, bounds: Vector3) {
        if (life <= 0f) return

        when (interaction) {
            is TouchInteraction.Attracting -> handleAttraction(interaction)
            is TouchInteraction.Exploding -> handleExplosion(interaction)
            is TouchInteraction.Idle -> handleIdle()
        }

        position += velocity
        wrapAround(bounds)
    }

    private fun handleAttraction(interaction: TouchInteraction.Attracting) {
        val vectorToAttractor = interaction.position - position
        val distance = vectorToAttractor.magnitude()

        // All particles frenzy slightly
        velocity += Vector3(
            (Random.nextFloat() - 0.5f) * FRENZY_STRENGTH,
            (Random.nextFloat() - 0.5f) * FRENZY_STRENGTH,
            (Random.nextFloat() - 0.5f) * FRENZY_STRENGTH
        )

        if (distance < ATTRACTION_RADIUS) {
            val closenessFactor = (1f - (distance / ATTRACTION_RADIUS)).coerceAtLeast(0f)

            // Attract towards the touch point
            velocity += vectorToAttractor.normalized() * (ATTRACTION_STRENGTH * closenessFactor)

            // Swirl around the touch point (on the XY plane)
            val swirlVector = Vector3(-vectorToAttractor.y, vectorToAttractor.x, 0f).normalized()
            velocity += swirlVector * (SWIRL_STRENGTH * closenessFactor)

            // "Tornado" lift towards the camera
            velocity.z -= TORNADO_LIFT_STRENGTH * closenessFactor * closenessFactor

            velocity *= FRICTION
        }
    }

    private fun handleExplosion(interaction: TouchInteraction.Exploding) {
        if (life == 1.0f) { // Apply explosion force only once
            val vectorFromExplosion = position - interaction.from
            velocity += vectorFromExplosion.normalized() * DISSIPATION_STRENGTH
        }
        life -= 0.02f
        if (life < 0f) life = 0f
    }

    private fun handleIdle() {
        if (life < 1.0f) {
            // Particle is dissipating, continue until it fades
            life -= 0.02f
            if (life < 0f) {
                life = 0f
                position = initialPosition.copy() // Reset fully
                velocity = initialVelocity.copy()
            }
        } else if (life == 0f || position != initialPosition) {
            // Fully reset after dissipation or if moved
            life = 1.0f
            position = initialPosition.copy()
            velocity = initialVelocity.copy()
        }
        velocity = velocity.times(0.97f) + (initialVelocity.times(0.03f))
    }

    private fun wrapAround(bounds: Vector3) {
        // When particles go too far forward or back, reset them to the far plane
        if (position.z < -1f) position.z = bounds.z // Reset to back
        if (position.z > bounds.z) position.z = -1f // Come from behind camera

        if (position.x < -bounds.x) position.x = bounds.x
        if (position.x > bounds.x) position.x = -bounds.x

        if (position.y < -bounds.y) position.y = bounds.y
        if (position.y > bounds.y) position.y = -bounds.y
    }
}