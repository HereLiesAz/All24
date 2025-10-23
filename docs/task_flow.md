# All24 User Task Flows

This document outlines the primary user task flows within the All24 application, detailing how users will interact with the app's core features.

## 1. The Onboarding Flow

This flow describes the first-time user experience.

1.  **Launch App:** The user opens the app for the first time.
2.  **View Splash Screen:** The user sees the cinematic splash screen animation (placeholder).
3.  **Interact with Welcome Carousel:** The user swipes through the three-panel carousel to learn about the app's core concepts.
4.  **Access Auth Screen:** From the final panel of the carousel, the user can choose to sign in or create an account. Tapping either option navigates them to the `AuthScreen`.

## 2. The Main Navigation Flow

This flow describes how a user navigates the main sections of the app after onboarding or signing in.

1.  **Enter Main Screen:** After completing the onboarding or signing in, the user is navigated to the `MainScreen`.
2.  **Use Bottom Navigation:** The user can use the `BottomAppBar` to navigate between the `HomeScreen` ("Lists"), `MapScreen` ("Map"), and `ProfileScreen` ("Profile").

## 3. The Authentication Flow

This flow describes how a user signs in or creates an account.

1.  **Navigate to Profile:** The user taps the "Profile" icon in the bottom navigation bar.
2.  **Access Auth Screen:** If the user is not signed in, they are presented with a "Login / Sign Up" button. Tapping this button navigates them to the `AuthScreen`.
3.  **Choose Authentication Method:** The user can choose to sign in with Google, or with an email and password.
4.  **Sign In with Google:**
    *   The user taps the "Sign in with Google" button.
    *   The Google Sign-In flow is initiated.
    *   Upon successful sign-in, the user is authenticated with Firebase and navigated back to the `ProfileScreen`.
5.  **Sign In with Email/Password:**
    *   The user enters their email and password and taps the "Sign In" button (UI only).
6.  **Create Account:**
    *   The user taps the "Create Account" button (UI only).

## 4. The Discovery Flow: Finding a New Experience

This is the primary user journey, focused on proactive and serendipitous discovery.

1.  **Launch App:** The user opens the All24 app.
2.  **Browse Home Feed:** The user is immediately presented with the Home Feed.
3.  **Scroll and Explore:** The user scrolls through the list of venues.
4.  **Select a Venue:** The user taps on a venue card.
5.  **View Detail Screen:** A shared element transition animates the card into the `DetailScreen`.
6.  **Explore Venue Details:** The user can now explore the rich content on the `DetailScreen`.

## 5. The "Vibe Check" Flow: Leaving Feedback

This flow describes how a user contributes to the community by leaving their own feedback.

1.  **Navigate to a Venue:** The user first navigates to the `DetailScreen` of a venue they have visited.
2.  **Vouch for the Venue:** The user can tap the "Vouch" button in the `BottomAppBar` to endorse the place.
3.  **Leave a Comment:** The user can leave a comment by filling in the Mad Libs-style prompts in the "Leave a Vibe Check" section.
4.  **Submit Comment:** The user taps the "Submit" button to submit their comment (UI only).

## 6. The Profile Engagement Flow: Reviewing Personal Activity

This flow describes how a user interacts with their personal space within the app.

1.  **Navigate to Profile:** The user taps the "Profile" icon in the bottom navigation bar.
2.  **View Profile Screen:** The user is taken to their `ProfileScreen`, where they can see their display name, email, "Vouched" places, "Tastemaker" comments, and badges.
3.  **Create a Collection:** The user can create a new collection of venues.
4.  **View a Collection:** The user can view their created collections.
5.  **Share a Collection:** The user can share a collection with friends.
6.  **Sign Out:** The user can tap the "Return to Ghost" button to sign out of their account.
