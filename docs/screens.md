# All24 Application Screens

This document provides an overview of the main screens in the All24 application, as defined in the conceptual blueprint.

## Onboarding Screen

The Onboarding Screen is the first experience for new users, designed to be a cinematic and informative introduction to the app.

*   **Layout:** A three-panel, horizontally-scrolling carousel.
*   **Content:**
    *   **Panel 1: "Welcome to the Real NOLA."** Explains the "Top 24" philosophy.
    *   **Panel 2: "Your Vibe is the New Five Stars."** Introduces the "Vibe Check" and "Vouch" system.
    *   **Panel 3: "Meet Your Guides."** Introduces the roles of Curators and Creators, and includes sign-in options.
*   **Animation:** The onboarding flow begins with a custom splash screen animation where the All24 logo is drawn and morphs into a progress indicator.

**Implementation Notes:**
* The `OnboardingScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/screens/onboarding/OnboardingScreen.kt`.
* A placeholder for the splash screen animation is included.

## Main Screen

The Main Screen is the main container for the app after the user has completed onboarding or signed in.

*   **Layout:** A `Scaffold` with a `BottomAppBar`.
*   **Navigation:** The `BottomAppBar` provides navigation between the `HomeScreen`, `MapScreen`, and `ProfileScreen`.

**Implementation Notes:**
* The `MainScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/screens/MainScreen.kt`.

## The Home Feed (Lists Screen)

The Home Feed is the primary screen of the application and the user's main entry point for discovery.

*   **Layout:** A single, uncluttered vertical column of "list item covers" with a parallax background.
*   **Content:** Each cover represents an establishment on a "Top 24" list and features a full-bleed photograph with a scrim, the venue's name, its rank, and a witty summary line.
*   **Interaction:** Vertical scrolling, a custom pull-to-refresh animation, and tapping a card to navigate to the Detail View.

**Implementation Notes:**
* The `HomeScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/screens/home/HomeScreen.kt`.
* A parallax background effect has been implemented.

## The Detail View

The Detail View is a full-screen "article" that provides in-depth information about a specific establishment.

*   **Layout:** A collapsing toolbar layout where the header image parallaxes and shrinks as the user scrolls.
*   **Content Modules:**
    *   "The All24 Take"
    *   "Creator Takes" carousel with video playback in a modal dialog.
    *   "The Vitals" with an animated 24-hour indicator.
    *   "The People's Voice"
    *   "Know Before You Geaux"
*   **Interaction Bar:** A `BottomAppBar` with a `Split` button for "Vouch," "Share," and "Add to Collection" actions.

## The Profile Screen

The Profile Screen is the user's personal culinary scrapbook.

*   **Layout:** A `CollapsingToolbarScaffold` with a header that collapses on scroll.
*   **Content Modules:**
    *   User avatar, display name, and "Tastemaker" badge.
    *   A `LazyVerticalStaggeredGrid` for a masonry-style layout of "Vouched" places.
    *   A "Collections" section.
    *   A "Badges" section for gamification achievements.

## Collections Screen

The Collections Screen displays a user-created collection of venues.

*   **Layout:** A list of venues within a specific collection.
*   **Interaction:** Users can view the venues in their collection and share the collection with friends.

## The Map Screen

The Map Screen is a secondary discovery tool for users who have a specific location in mind.

*   **Layout:** A traditional map-based interface.

**Implementation Notes:**
* A placeholder `MapScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/screens/map/MapScreen.kt`.

## The Auth Screen

The Auth Screen provides a way for users to sign in or create an account.

*   **Layout:** A simple layout with fields for email and password, and buttons for signing in, creating an account, and signing in with Google.

## Navigation

The app's navigation is orchestrated by `AppNavigation.kt`.

*   **Initial Flow:** The app starts at the `OnboardingScreen`. Upon completion, it navigates to the `MainScreen`.
*   **Main Flow:** The `MainScreen` contains a nested `NavHost` that handles navigation between the `HomeScreen`, `MapScreen`, and `ProfileScreen`.
