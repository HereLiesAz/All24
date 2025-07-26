package com.hereliesaz.all24.services

import android.content.Context
import android.location.Geocoder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.authStateFlow
import com.google.firebase.firestore.*
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.data.Submission
import com.hereliesaz.all24.data.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.util.Date

class FirebaseService {

    private val auth: FirebaseAuth = Firebase.auth
    private val db: FirebaseFirestore = Firebase.firestore

    val currentUser get() = auth.currentUser
    val authStateChanges = auth.authStateFlow()

    // --- Auth & User Functions ---
    suspend fun signInAnonymously() {
        if (auth.currentUser == null) {
            auth.signInAnonymously().await()
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

    fun getUserModelStream(uid: String): Flow<UserModel?> {
        return db.collection("users").document(uid).snapshots().map { it.toObject<UserModel>() }
    }

    // --- Read Functions ---
    suspend fun getPlaces(): List<Place> {
        return db.collection("places").get().await().toObjects()
    }

    suspend fun getAllReviews(): List<Review> {
        return db.collection("reviews").get().await().toObjects()
    }

    suspend fun getPlaceById(placeId: String): Place? {
        return db.collection("places").document(placeId).get().await().toObject<Place>()
    }

    suspend fun getSubmissionById(submissionId: String): Submission? {
        return db.collection("place_submissions").document(submissionId).get().await().toObject<Submission>()
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

    fun getPlacesForOwnerStream(ownerId: String) = db.collection("places")
        .whereEqualTo("ownerId", ownerId)
        .snapshots()
        .map { it.toObjects<Place>() }

    // --- Write Functions ---
    suspend fun addReview(placeId: String, text: String, vote: String) {
        val user = currentUser ?: throw Exception("User not authenticated")
        val review = Review(placeId = placeId, userId = user.uid, text = text, vote = vote, timestamp = Date())
        db.collection("reviews").add(review).await()
    }

    suspend fun verifyReview(reviewId: String, vote: String) {
        val userId = currentUser?.uid ?: throw Exception("User not authenticated")
        val reviewRef = db.collection("reviews").document(reviewId)
        db.runTransaction { transaction ->
            val fieldToUpdate = if (vote == "endorse") "endorsedBy" else "avoidedBy"
            val fieldToRemoveFrom = if (vote == "endorse") "avoidedBy" else "endorsedBy"
            transaction.update(reviewRef, fieldToUpdate, FieldValue.arrayUnion(userId))
            transaction.update(reviewRef, fieldToRemoveFrom, FieldValue.arrayRemove(userId))
        }.await()
    }

    suspend fun submitPlace(name: String, description: String, address: String, category: String) {
        val user = currentUser ?: throw Exception("User not authenticated")
        val submission = Submission(name = name, description = description, address = address, category = category, submittedBy = user.uid, submittedAt = Date(), status = "pending")
        db.collection("place_submissions").add(submission).await()
    }

    suspend fun updatePlace(placeId: String, name: String, description: String) {
        db.collection("places").document(placeId).update(mapOf("name" to name, "description" to description)).await()
    }

    suspend fun approveSubmission(
        submission: Submission,
        context: Context,
        ownerId: String? = null,
    ) {
        val newPlaceRef = db.collection("places").document()
        val submissionRef = db.collection("place_submissions").document(submission.id)

        // Geocode the address string into a GeoPoint.
        var location: GeoPoint? = null
        if (Geocoder.isPresent()) {
            try {
                val geocoder = Geocoder(context)
                // In a real app, you might use the newer, callback-based API for Android 13+
                val addresses = geocoder.getFromLocationName(submission.address, 1)
                if (addresses?.isNotEmpty() == true) {
                    location = GeoPoint(addresses[0].latitude, addresses[0].longitude)
                }
            } catch (e: IOException) {
                // Log the error or handle it as needed. For now, location remains null.
                e.printStackTrace()
            }
        }

        val newPlace = Place(
            id = newPlaceRef.id,
            name = submission.name,
            description = submission.description,
            location = location,
            category = submission.category,
            ownerId = ownerId
        )

        db.runBatch { batch ->
            batch.set(newPlaceRef, newPlace)
            batch.delete(submissionRef)
        }.await()
    }

    suspend fun denySubmission(submissionId: String) {
        db.collection("place_submissions").document(submissionId).delete().await()
    }
}