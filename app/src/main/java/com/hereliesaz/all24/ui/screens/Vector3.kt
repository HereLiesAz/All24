package com.hereliesaz.all24.ui.screens

import kotlin.math.sqrt

data class Vector3(var x: Float, var y: Float, var z: Float) {
    operator fun plus(other: Vector3) = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vector3) = Vector3(x - other.x, y - other.y, z - other.z)
    operator fun times(scalar: Float) = Vector3(x * scalar, y * scalar, z * scalar)
    operator fun div(scalar: Float): Vector3 {
        if (scalar == 0f) return Vector3(0f, 0f, 0f)
        return Vector3(x / scalar, y / scalar, z / scalar)
    }

    fun magnitude(): Float = sqrt(x * x + y * y + z * z)
    fun normalized(): Vector3 {
        val mag = magnitude()
        return if (mag == 0f) Vector3(0f, 0f, 0f) else this / mag
    }
}