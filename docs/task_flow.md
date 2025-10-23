# All24 User Task Flows

This document outlines the primary user task flows within the All24 application, detailing how users will interact with the app's core features.

## 1. The Authentication Flow

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

## 2. The Discovery Flow: Finding a New Experience

This is the primary user journey, focused on proactive and serendipitous discovery.

1.  **Launch App:** The user opens the All24 app.
2.  **Browse Home Feed:** The user is immediately presented with the Home Feed, a visually rich, endlessly scrollable feed of "Top 24" lists.
3.  **Scroll and Explore:** The user scrolls vertically through the lists, browsing the "covers" of different establishments. Each cover provides a compelling image and a witty summary to entice the user.
4.  **Select a Venue:** The user finds a venue that piques their interest and taps on its card.
5.  **View Detail Screen:** A fluid, shared element transition expands the card into the full-screen Detail View.
6.  **Explore Venue Details:** The user can now explore the rich content on the Detail View.
7.  **Take Action:** From the Detail View, the user can choose to get directions, call the venue, or bookmark the list for later.

## 3. The "Vibe Check" Flow: Leaving Feedback

This flow describes how a user contributes to the community by leaving their own feedback.

1.  **Navigate to a Venue:** The user first navigates to the Detail View of a venue they have visited.
2.  **Vouch for the Venue:** The user can tap the "Vouch" button to endorse the place.
3.  **Leave a Comment:** The user can leave a comment by filling in the Mad Libs-style prompts in the "Leave a Vibe Check" section.
4.  **Submit Comment:** The user taps the "Submit" button to submit their comment (UI only).

## 4. The Profile Engagement Flow: Reviewing Personal Activity

This flow describes how a user interacts with their personal space within the app.

1.  **Navigate to Profile:** The user taps the "Profile" icon in the bottom navigation bar.
2.  **View Profile Screen:** The user is taken to their Profile Screen, where they can see their display name, email, "Vouched" places, and "Tastemaker" comments.
3.  **Sign Out:** The user can tap the "Return to Ghost" button to sign out of their account.
