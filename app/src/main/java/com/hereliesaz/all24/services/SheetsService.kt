package com.hereliesaz.all24.services

import com.hereliesaz.all24.data.Place
import com.hereliesaz.all24.data.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class SheetsService {

    // --- MOCK DATA ---
    private val mockPlaces = listOf(
        Place(
            id = "place_01",
            name = "The Serpent's Coil",
            description = "A dimly lit bar known for its potent cocktails and whispered secrets.",
            category = "bar",
            tags = listOf("24-Hour Bars", "Happy Hours")
        ),
        Place(
            id = "place_02",
            name = "Eulalie's Absinthe House",
            description = "Green light spills from the windows of this timeless establishment.",
            category = "bar",
            tags = listOf("24-Hour Bars")
        ),
        Place(
            id = "place_03",
            name = "The Gilded Cage",
            description = "Once an opulent theater, now a sprawling, decadent nightclub.",
            category = "club",
            tags = listOf("Events", "Happy Hours")
        ),
        Place(
            id = "place_04",
            name = "Midnight Grub",
            description = "The best post-bar food you can find.",
            category = "food",
            tags = listOf("Late-Night Food")
        ),
        Place(
            id = "place_05",
            name = "The Wandering Chef",
            description = "A new menu and location every week.",
            category = "food",
            tags = listOf("Pop-ups", "Late-Night Food")
        )
    )

    private val mockReviews = listOf(
        Review(
            id = "review_01",
            placeId = "place_01",
            userId = "user_a",
            text = "The Sazerac here is a religious experience.",
            vote = "endorse",
            timestamp = Date()
        ),
        Review(
            id = "review_02",
            placeId = "place_02",
            userId = "user_b",
            text = "Felt like I was stepping into another century. The green fairy is real.",
            vote = "endorse",
            timestamp = Date()
        ),
        Review(
            id = "review_03",
            placeId = "place_01",
            userId = "user_c",
            text = "Too dark, and the bartender stared at me funny.",
            vote = "avoid",
            timestamp = Date()
        )
    )
    // --- END MOCK DATA ---


    // --- Read Functions ---
    suspend fun getPlaces(): List<Place> {
        return withContext(Dispatchers.IO) {
            // Return the mock data instead of making a network call.
            mockPlaces
        }
    }

    suspend fun getAllReviews(): List<Review> {
        return withContext(Dispatchers.IO) {
            // Return the mock data.
            mockReviews
        }
    }

    suspend fun getSubmissions(): List<com.hereliesaz.all24.data.Submission> {
        return emptyList()
    }


    // --- Write Functions ---
    // The write functions remain placeholders for your real implementation.
    suspend fun submitPlace(
        idToken: String,
        name: String,
        description: String,
        address: String,
        category: String,
    ) {
        println("Pretending to submit place: $name")
    }
}