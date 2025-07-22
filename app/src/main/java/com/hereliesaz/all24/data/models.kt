package com.hereliesaz.all24.data

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class UserModel(
    @DocumentId val uid: String = "",
    val email: String = "",
    val role: String = "user" // 'user', 'business', 'admin'
)

data class Place(
    @DocumentId val id: String = "",
    val name: String = "",
    val description: String = "",
    val location: GeoPoint? = null,
    val category: String = "bar",
    val ownerId: String? = null
)

data class Review(
    @DocumentId val id: String = "",
    val placeId: String = "",
    val userId: String = "",
    val text: String = "",
    val vote: String = "endorse",
    @ServerTimestamp val timestamp: Date? = null,
    val endorsedBy: List<String> = emptyList(),
    val avoidedBy: List<String> = emptyList(),
    val isAdminReview: Boolean = false
)

data class Submission(
    @DocumentId val id: String = "",
    val name: String = "",
    val description: String = "",
    val address: String = "",
    val category: String = "bar",
    val submittedBy: String = "",
    @ServerTimestamp val submittedAt: Date? = null,
    val status: String = "pending"
)