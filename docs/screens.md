# All24 Application Screens

This document provides an overview of the main screens in the All24 application, as defined in the conceptual blueprint.

## The Home Feed (Lists Screen)

The Home Feed is the primary screen of the application and the user's main entry point for discovery.

*   **Layout:** A single, uncluttered vertical column of "list item covers."
*   **Content:** Each cover represents an establishment on a "Top 24" list and features a full-bleed photograph, the venue's name, its rank, and a witty summary line.
*   **Interaction:** The only interactions are vertical scrolling to browse the feed and tapping a card to navigate to the Detail View. All complexity is intentionally hidden within the cards themselves.

## The Detail View

The Detail View is a full-screen "article" that provides in-depth information about a specific establishment. It is revealed through a fluid shared element transition when a user taps a card on the Home Feed.

*   **Layout:** A rich, multi-faceted layout that presents a complete portrait of the venue.
*   **Content Modules:**
    *   **The All24 Take:** The official 150-word review from the in-house curators.
    *   **Creator Takes:** A horizontal carousel of short-form videos and photo galleries from verified local influencers.
    *   **The Vitals:** Essential information, including address (with a link to maps), hours, phone number, and a link to the menu.
    *   **The People's Voice:** A curated selection of 3-4 of the most insightful user comments.
    *   **"Know Before You Geaux":** A dedicated section for practical, insider tips.
*   **"Vouch" Button:** A button that allows users to endorse the venue.
*   **Prompted Comment Submission:** A UI for submitting "Vibe Check" comments using Mad Libs-style prompts.

**Implementation Notes:**
* The Detail Screen is implemented in `app/src/main/java/com/hereliesaz/all24/ui/screens/detail/DetailScreen.kt`.
* The screen currently uses placeholder data. A ViewModel will be implemented in a future step to fetch data for a specific venue.
* A shared element transition is implemented between the `HomeScreen` and `DetailScreen`.

## The Map Screen

The Map Screen is a secondary discovery tool for users who have a specific location in mind.

*   **Layout:** A traditional map-based interface.
*   **Content:** The map will display all All24-vetted spots in the user's vicinity.
*   **Interaction:** Users can pan and zoom the map to explore different neighborhoods and tap on a venue to see a preview of its details.

## The Profile Screen

The Profile Screen is the user's personal space within the app, designed to be a culinary scrapbook of their New Orleans journey.

*   **Layout:** A visually engaging and personalized layout.
*   **Content Modules:**
    *   Display name and email of the signed-in user.
    *   A "Sign Out" button.
    *   A grid of "Vouched" places (with placeholder data).
    *   A grid of "Tastemaker" comments (with placeholder data).

**Implementation Notes:**
* The `ProfileScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/screens/ProfileScreen.kt`.

## The Auth Screen

The Auth Screen provides a way for users to sign in or create an account.

*   **Layout:** A simple layout with fields for email and password, and buttons for signing in, creating an account, and signing in with Google.
*   **Functionality:**
    *   Email/password sign-in and account creation (UI only).
    *   Google Sign-In with Firebase Authentication.

**Implementation Notes:**
* The `AuthScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/auth/AuthScreen.kt`.

## Navigation

A minimal bottom navigation bar provides access to the app's core sections with three clear icons:

*   **Lists:** The default home screen.
*   **Map:** The map-based discovery tool.
*   **Profile:** The user's personal space.
