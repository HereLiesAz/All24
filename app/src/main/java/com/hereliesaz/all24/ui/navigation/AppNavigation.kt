package com.hereliesaz.all24.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hereliesaz.all24.ui.auth.AuthScreen
import com.hereliesaz.all24.ui.screens.add_review.AddReviewScreen
import com.hereliesaz.all24.ui.screens.admin.AdminDashboardScreen
import com.hereliesaz.all24.ui.screens.admin.AdminSubmissionDetailScreen
import com.hereliesaz.all24.ui.screens.business.BusinessDashboardScreen
import com.hereliesaz.all24.ui.screens.business.EditPlaceScreen
import com.hereliesaz.all24.ui.screens.place_detail.PlaceDetailScreen
import com.hereliesaz.all24.ui.screens.place_detail.PlaceDetailViewModel
import com.hereliesaz.all24.ui.screens.ProfileScreen
import com.hereliesaz.all24.ui.screens.submit_place.SubmitPlaceScreen
import com.hereliesaz.all24.ui.screens.top_reviews.TopReviewsScreen
import com.hereliesaz.all24.ui.screens.VibeScreen

sealed class Screen(val route: String) {
    object Vibe : Screen("vibe")
    object Auth : Screen("auth")
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
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Vibe.route) {
        composable(Screen.Vibe.route) { VibeScreen(navController) }
        composable(Screen.Auth.route) { AuthScreen(onSuccessfulAuth = { navController.popBackStack() }) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.TopReviews.route) { TopReviewsScreen(navController) }
        composable(Screen.SubmitPlace.route) { SubmitPlaceScreen(navController) }
        composable(Screen.AdminDashboard.route) { AdminDashboardScreen(navController) }
        composable(Screen.BusinessDashboard.route) { BusinessDashboardScreen(navController) }
        composable(
            route = Screen.AdminSubmissionDetail.route,
            arguments = listOf(navArgument("submissionId") { type = NavType.StringType })
        ) {
            AdminSubmissionDetailScreen(navController)
        }
        composable(
            route = Screen.PlaceDetail.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) {
            PlaceDetailScreen(navController, it.arguments?.getString("placeId")!!)
        }
        composable(
            route = Screen.AddReview.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) {
            AddReviewScreen(navController, it.arguments?.getString("placeId")!!)
        }
        composable(
            route = Screen.EditPlace.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) {
            EditPlaceScreen(navController)
        }
    }
}