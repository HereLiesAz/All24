package com.hereliesaz.all24.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.data.Submission
import com.hereliesaz.all24.data.UserModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.Date

class FirebaseService {

    private val auth: FirebaseAuth = Firebase.auth
    private val db: FirebaseFirestore = Firebase.firestore

    val currentUser get() = auth.currentUser
    val authStateChanges = auth.authStateFlow()

    // --- Auth Functions ---
    suspend fun signInAnonymously() {
        if (auth.currentUser == null) {
            try {
                auth.signInAnonymously().await()
            } catch (e: Exception) {
                println("Anonymous sign-in failed: ${e.message}")
            }
        }
    }

    suspend fun signUpWithEmail(email: String, password: String) {
        val credential = auth.createUserWithEmailAndPassword(email, password).await()
        val user = credential.user
        if (user != null) {
            val newUser = UserModel(uid = user.uid, email = user.email!!)
            db.collection("users").document(user.uid).set(newUser).await()
        }
    }

    suspend fun signInWithEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun signOut() {
        auth.signOut()
        signInAnonymously()
    }

    // --- Firestore Read Functions ---
    suspend fun getPlaces(): List<Place> {
        return db.collection("places").get().await().toObjects()
    }

    suspend fun getPlaceById(placeId: String): Place? {
        return db.collection("places").document(placeId).get().await().toObject<Place>()
    }

    fun getAdminReviewsStream() = db.collection("reviews")
        .whereEqualTo("isAdminReview", true)
        .orderBy("timestamp", Query.Direction.DESCENDING)
        .snapshots()
        .map { it.toObjects<Review>() }

    fun getReviewsForPlaceStream(placeId: String) = db.collection("reviews")
        .whereEqualTo("placeId", placeId)
        .orderBy("timestamp", Query.Direction.DESCENDING)
        .snapshots()
        .map { it.toObjects<Review>() }

    fun getSubmissionsStream() = db.collection("place_submissions")
        .whereEqualTo("status", "pending")
        .snapshots()
        .map { it.toObjects<Submission>() }

    // --- Firestore Write Functions ---
    suspend fun addReview(placeId: String, text: String, vote: String) {
        val user = currentUser ?: throw Exception("User not authenticated")
        val review = Review(
            placeId = placeId,
            userId = user.uid,
            text = text,
            vote = vote,
            timestamp = Date()
        )
        db.collection("reviews").add(review).await()
    }

    suspend fun verifyReview(reviewId: String, vote: String) {
        val userId = currentUser?.uid ?: throw Exception("User not authenticated")
        val reviewRef = db.collection("reviews").doc(reviewId)

        db.runTransaction { transaction ->
            val fieldToUpdate = if (vote == "endorse") "endorsedBy" else "avoidedBy"
            val fieldToRemoveFrom = if (vote == "endorse") "avoidedBy" else "endorsedBy"

            transaction.update(reviewRef, fieldToUpdate, FieldValue.arrayUnion(userId))
            transaction.update(reviewRef, fieldToRemoveFrom, FieldValue.arrayRemove(userId))
        }.await()
    }

    suspend fun submitPlace(name: String, description: String, address: String, category: String) {
        val user = currentUser ?: throw Exception("User not authenticated")
        val submission = Submission(
            name = name,
            description = description,
            address = address,
            category = category,
            submittedBy = user.uid,
            submittedAt = Date(),
            status = "pending"
        )
        db.collection("place_submissions").add(submission).await()
    }
}
