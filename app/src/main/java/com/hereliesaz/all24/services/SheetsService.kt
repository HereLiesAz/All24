package com.hereliesaz.all24.services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hereliesaz.all24.BuildConfig
import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import com.hereliesaz.all24.data.Submission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class SheetsService {

    private val gson = Gson()

    // Base URL for read operations from the Google Apps Script
    private val appsScriptUrl = BuildConfig.APPS_SCRIPT_URL

    // URL for the secure 'submitPlace' Google Cloud Function
    // NOTE: This assumes the Cloud Function URL is stored in the same buildConfigField.
    // You might want to create a separate one for clarity (e.g., CLOUD_FUNCTION_URL).
    private val submitPlaceUrl = BuildConfig.APPS_SCRIPT_URL


    private suspend inline fun <reified T> fetchData(sheetName: String): T {
        val urlString = "$appsScriptUrl?sheet=$sheetName"
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = reader.readText()
            reader.close()
            // Use TypeToken to handle generic list parsing with Gson
            val type = object : TypeToken<T>() {}.type
            return gson.fromJson(response, type)
        } else {
            throw Exception("Failed to fetch data from $sheetName. HTTP Error: ${connection.responseCode}")
        }
    }

    // --- Read Functions ---
    suspend fun getPlaces(): List<Place> {
        return withContext(Dispatchers.IO) {
            try {
                fetchData<List<Place>>("places")
            } catch (e: Exception) {
                println("Error fetching places: ${e.message}")
                emptyList()
            }
        }
    }

    suspend fun getAllReviews(): List<Review> {
        return withContext(Dispatchers.IO) {
            try {
                fetchData<List<Review>>("reviews")
            } catch (e: Exception) {
                println("Error fetching reviews: ${e.message}")
                emptyList()
            }
        }
    }

    suspend fun getSubmissions(): List<Submission> {
        // This assumes you have an Apps Script part for this or a secure Cloud Function
        return emptyList()
    }


    // --- Write Functions ---
    suspend fun submitPlace(
        idToken: String,
        name: String,
        description: String,
        address: String,
        category: String,
    ) {
        return withContext(Dispatchers.IO) {
            val url = URL(submitPlaceUrl) // This should be your Cloud Function URL
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Authorization", "Bearer $idToken")

            val jsonPayload = """
                {
                    "name": "$name",
                    "description": "$description",
                    "address": "$address",
                    "category": "$category"
                }
            """.trimIndent()

            val writer = OutputStreamWriter(connection.outputStream)
            writer.write(jsonPayload)
            writer.flush()
            writer.close()

            val responseCode = connection.responseCode
            if (responseCode != HttpURLConnection.HTTP_OK) {
                val errorReader = BufferedReader(InputStreamReader(connection.errorStream))
                val errorResponse = errorReader.readText()
                errorReader.close()
                throw Exception("Submission failed. HTTP Error: $responseCode. Message: $errorResponse")
            }
        }
    }

    suspend fun addReview(placeId: String, text: String, vote: String, idToken: String) {
        // TODO: Implement the backend Cloud Function for adding a review.
        // This function would be very similar to submitPlace, requiring an idToken.
        println("Review submission for $placeId is not yet implemented on the backend.")
        // For now, we simulate success without doing anything.
    }
}