package com.hereliesaz.all24.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.hereliesaz.all24.ui.auth.AuthScreen
import com.hereliesaz.all24.ui.screens.ProfileScreen
import com.hereliesaz.all24.ui.screens.VibeScreen
import com.hereliesaz.all24.ui.screens.add_review.AddReviewScreen
import com.hereliesaz.all24.ui.screens.admin.AdminDashboardScreen
import com.hereliesaz.all24.ui.screens.admin.AdminSubmissionDetailScreen
import com.hereliesaz.all24.ui.screens.business.BusinessDashboardScreen
import com.hereliesaz.all24.ui.screens.business.EditPlaceScreen
import com.hereliesaz.all24.ui.screens.place_detail.PlaceDetailScreen
import com.hereliesaz.all24.ui.screens.submit_place.SubmitPlaceScreen
import com.hereliesaz.all24.ui.screens.top.reviews.TopReviewsScreen

// The Screen sealed class defines all possible navigation destinations in a type-safe way.
sealed class Screen(val route: String) {
    object Vibe : Screen("vibe")
    object Auth : Screen("auth") // The Confessional
    object Profile : Screen("profile")
    object TopReviews : Screen("top_reviews")
    object SubmitPlace : Screen("submit_place")
    object AdminDashboard : Screen("admin_dashboard")
    object BusinessDashboard : Screen("business_dashboard")
    object AdminSubmissionDetail : Screen("admin_submission_detail/{submissionId}") {
        fun createRoute(submissionId: String) = "admin_submission_detail/$submissionId"
    }
    object PlaceDetail : Screen("place_detail/{placeId}") {
        fun createRoute(placeId: String) = "place_detail/$placeId"
    }
    object AddReview : Screen("add_review/{placeId}") {
        fun createRoute(placeId: String) = "add_review/$placeId"
    }
    object EditPlace : Screen("edit_place/{placeId}") {
        fun createRoute(placeId: String) = "edit_place/$placeId"
    }
}

@Composable
fun AppNavigation(googleSignInClient: GoogleSignInClient) {
    val navController = NavController()

    // NavHost is the container for all navigation destinations.
    NavHost(navController = navController, startDestination = Screen.Vibe.route) {

        composable(Screen.Vibe.route) {
            VibeScreen(navController)
        }

        // The dedicated route for authentication, a separate, compartmentalized ritual.
        composable(Screen.Auth.route) {
            AuthScreen(
                navController = navController,
                googleSignInClient = googleSignInClient
            )
        }

        // The route for viewing the user's current state of being.
        composable(Screen.Profile.route) {
            ProfileScreen(
                navController = navController,
                googleSignInClient = googleSignInClient
            )
        }

        composable(Screen.TopReviews.route) {
            TopReviewsScreen(navController)
        }

        composable(Screen.SubmitPlace.route) {
            SubmitPlaceScreen(navController)
        }

        composable(Screen.AdminDashboard.route) {
            AdminDashboardScreen(navController)
        }

        composable(Screen.BusinessDashboard.route) {
            BusinessDashboardScreen(navController)
        }

        // Route for viewing the details of a specific submission. It requires a 'submissionId'.
        composable(
            route = Screen.AdminSubmissionDetail.route,
            arguments = listOf(navArgument("submissionId") { type = NavType.StringType })
        ) {
            AdminSubmissionDetailScreen(navController)
        }

        // Route for viewing the details of a specific place. It requires a 'placeId'.
        composable(
            route = Screen.PlaceDetail.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) { backStackEntry ->
            PlaceDetailScreen(navController, backStackEntry.arguments?.getString("placeId"))
        }

        // Route for adding a review to a specific place. It requires a 'placeId'.
        composable(
            route = Screen.AddReview.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) { backStackEntry ->
            AddReviewScreen(navController, backStackEntry.arguments?.getString("placeId")!!)
        }

        // Route for editing a specific place. It requires a 'placeId'.
        composable(
            route = Screen.EditPlace.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) {
            EditPlaceScreen(navController)
        }
    }
}