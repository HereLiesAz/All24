package com.hereliesaz.all24.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.hereliesaz.all24.ui.auth.AuthScreen
import com.hereliesaz.all24.ui.screens.ProfileScreen
import com.hereliesaz.all24.ui.screens.add.review.AddReviewScreen
import com.hereliesaz.all24.ui.screens.admin.AdminDashboardScreen
import com.hereliesaz.all24.ui.screens.admin.AdminSubmissionDetailScreen
import com.hereliesaz.all24.ui.screens.business.BusinessDashboardScreen
import com.hereliesaz.all24.ui.screens.business.EditPlaceScreen
import com.hereliesaz.all24.ui.screens.carousel.MainCarouselScreen
import com.hereliesaz.all24.ui.screens.place.detail.PlaceDetailScreen
import com.hereliesaz.all24.ui.screens.place.list.PlaceListScreen
import com.hereliesaz.all24.ui.screens.submit.place.SubmitPlaceScreen
import androidx.compose.runtime.Composableimport com.hereliesaz.all24.ui.screens.top.reviews.TopReviewsScreen


sealed class Screen(val route: String) {
    object MainCarousel : Screen("main_carousel") // The new entry point
    object PlacesList : Screen("places_list")
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
fun AppNavigation(googleSignInClient: GoogleSignInClient) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainCarousel.route) {

        composable(Screen.MainCarousel.route) {
            MainCarouselScreen(navController)
        }

        composable(Screen.PlacesList.route) {
            PlacesListScreen(navController)
        }

        composable(Screen.Vibe.route) {
            VibeScreen(navController)
        }

        composable(Screen.Auth.route) {
            AuthScreen(
                navController = navController,
                googleSignInClient = googleSignInClient
            )
        }

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

        composable(
            route = Screen.AdminSubmissionDetail.route,
            arguments = listOf(navArgument("submissionId") { type = NavType.StringType })
        ) {
            AdminSubmissionDetailScreen(navController)
        }

        composable(
            route = Screen.PlaceDetail.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) { backStackEntry ->
            PlaceDetailScreen(navController, backStackEntry.arguments?.getString("placeId"))
        }



        composable(
            route = Screen.AddReview.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) { backStackEntry ->
            AddReviewScreen(navController, backStackEntry.arguments?.getString("placeId")!!)
        }

        composable(
            route = Screen.EditPlace.route,
            arguments = listOf(navArgument("placeId") { type = NavType.StringType })
        ) {
            EditPlaceScreen(navController)
        }
    }
}

@Composable
fun VibeScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}

@Composable
fun PlacesListScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}