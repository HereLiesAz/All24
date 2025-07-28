# Development Roadmap & Architectural Corrections

This document outlines pending development tasks and critical architectural issues that must be
resolved to ensure application stability and functionality.

***

### Architectural & Navigational Refactoring

- **Task: Decouple `PlaceDetailScreen` from `MainCarouselViewModel`**
    - **Objective**: To correct the architectural flaw where the detail screen depends on the
      carousel's ViewModel.
    - **Requirements**:
        1. [cite_start]Modify `PlaceDetailScreen.kt` to instantiate and use its dedicated
           `PlaceDetailViewModel`[cite: 178, 181].
        2. [cite_start]Ensure the `PlaceDetailViewModel` correctly fetches all data for a given
           `placeId` from the `SheetsService`[cite: 184].
        3. [cite_start]The `placeId` navigation argument must be made non-nullable in
           `AppNavigation.kt` to prevent silent failures[cite: 96].

- **Task: Prune Dead Navigation Routes**
    - **Objective**: To remove non-functional and deprecated routes from the navigation graph.
    - **Requirements**:
        1. [cite_start]Delete the composable routes for `VibeScreen` and `PlacesListScreen` from
           `AppNavigation.kt` to prevent runtime crashes[cite: 92].

- **Task: Refactor Authentication Flow**
    - **Objective**: To decouple the UI from the `GoogleSignInClient` and improve testability.
    - **Requirements**:
        1. [cite_start]Remove the `googleSignInClient` parameter from `AppNavigation` and all child
           composables[cite: 93].
        2. Create a dedicated authentication state holder or ViewModel that manages the sign-in
           process and exposes only the necessary state (e.g., `isSignedIn`, `idToken`) to the UI.

- **Task: Correct Shared Element Transition Implementation**
    - **Objective**: To align the navigation implementation with the standard Jetpack Compose
      patterns.
    - **Requirements**:
        1. [cite_start]Refactor `AppNavigation.kt` to remove the manual passing of
           `SharedTransitionScope` and `AnimatedVisibilityScope` as parameters[cite: 153, 179].
        2. Define the composable destinations within the `NavHost` to use the implicitly provided
           scopes.

***

### Feature Implementation & Bug Fixes

- **Task: Implement Missing ViewModel Logic**
    - **Objective**: To make all user-facing features fully functional.
    - **Requirements**:
        1. [cite_start]In `TopReviewsViewModel.kt`, uncomment and implement the `getPlaceForReview`
           function to correctly fetch place data from the `SheetsService`[cite: 221].
        2. [cite_start]In `AddReviewViewModel.kt`, implement the service call within `submitReview`
           so that users can successfully post reviews[cite: 108].
        3. Complete all other placeholder service calls across the ViewModels for admin and business
           logic.

- **Task: Implement Dynamic Category Generation**
    - **Objective**: To make the main carousel robust and data-driven.
    - **Requirements**:
        1. [cite_start]Modify `MainCarouselViewModel` to dynamically generate the list of categories
           from the unique `tags` found in the `Place` objects returned by the
           `SheetsService`[cite: 175]. The hardcoded list should be removed.

- **Task: Replace Mock Data Source**
    - **Objective**: To connect the application to a live backend.
    - **Requirements**:
        1. [cite_start]Replace the mock data in `SheetsService` with live calls to the production
           Google Sheets or Firestore database[cite: 576, 577].
        2. [cite_start]Ensure all ViewModels correctly handle loading, error, and empty states from
           the live data source[cite: 578].