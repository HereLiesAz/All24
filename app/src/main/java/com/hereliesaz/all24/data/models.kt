package com.hereliesaz.all24.data

import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class UserModel(
    val uid: String = "",
    val email: String = "",
    val role: String = "user" // 'user', 'business', 'admin'
)

data class Place(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val location: GeoPoint? = null,
    val category: String = "bar",
    val ownerId: String? = null // ID of the user with the 'business' role who owns this place
)

data class Review(
    val id: String = "",
    val placeId: String = "",
    val userId: String = "",
    val text: String = "",
    val vote: String = "endorse", // 'endorse' or 'avoid'
    @ServerTimestamp val timestamp: Date? = null,
    val endorsedBy: List<String> = emptyList(),
    val avoidedBy: List<String> = emptyList(),
    val isAdminReview: Boolean = false
)

data class Submission(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val address: String = "",
    val category: String = "bar",
    val submittedBy: String = "",
    @ServerTimestamp val submittedAt: Date? = null,
    val status: String = "pending"
)
