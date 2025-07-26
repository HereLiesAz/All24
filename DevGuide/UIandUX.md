# UI/UX Documentation

This document details the design, layout, interaction logic, and accessibility considerations for
the key screens in the All24 application.

## 1. `VibeScreen`

* **Purpose**: The main entry point and primary interaction screen of the application. It embodies
  the project's philosophy of serendipitous discovery by avoiding traditional navigation paradigms.
* **Visual Design & Theming**:
    * The background is a full-screen `@Composable` `Canvas` with its color bound to
      `MaterialTheme.colorScheme.background`, which is absolute black in the dark theme.
    * It renders an abstract particle system where particles drift slowly. The particles are simple
      white circles with varying alpha, creating a sense of depth and atmosphere. The animation is
      continuous and driven by an `infiniteRepeatable` `Animatable`.
* **Interaction**:
    * The primary interaction is a single tap gesture anywhere on the screen, detected via a
      `pointerInput` modifier.
    * A tap calls `viewModel.conjureRecommendations()` to fetch and display a new set of 1-3
      recommended places.
    * The list of recommendations is displayed in a dark, semi-transparent panel that animates into
      view from the bottom of the screen using `AnimatedVisibility`. Tapping a recommendation
      navigates to its `PlaceDetailScreen`.
* **Composable Breakdown**:
    * `VibeScreen(navController, viewModel)`: The main screen composable containing the primary
      `Box`
      layout.
    * `ParticleCanvas(modifier)`: A dedicated composable that handles the `Canvas` and all particle
      drawing logic, including the animation `LaunchedEffect` and the `drawCircle` calls for each
      particle.
    * `VibeNavButton(icon, tooltip, alignment, onClick)`: A reusable composable within the
      `VibeScreen`'s `BoxScope` for the four corner navigation icons, ensuring consistent padding
      and
      styling.
* **State Handling**:
    * `VibeUiState.recommendations`: This `List<Place>` populates the items in the animated
      recommendation panel.
    * `VibeUiState.showRecommendations`: This `Boolean` controls the `visible` property of the
      `AnimatedVisibility` composable, triggering the enter/exit animations for the recommendation
      panel.
* **Accessibility (`a11y`)**:
    * The `VibeNavButton` `IconButton`s must have a `contentDescription` set to the `tooltip`
      parameter (e.g., "Profile", "Top Reviews") to ensure screen readers can announce their
      function.
    * The touch targets for the corner icons must adhere to a minimum size of 48x48dp via padding to
      be easily tappable.
    * The `ParticleCanvas` is decorative and should be appropriately handled for screen readers,
      possibly by making the container non-focusable.

## 2. `PlaceDetailScreen`

* **Purpose**: To display all relevant information for a single `Place`, including its curated
  description and a full list of its associated reviews.
* **Layout**:
    * A `Scaffold` with a transparent `TopAppBar` displaying the place's name.
    * The body is a `LazyColumn` to efficiently display a potentially long list of reviews.
    * A `FloatingActionButton` provides the entry point for adding a new review.
* **Content Hierarchy & Logic**:
    * The screen first displays the place's name and description.
    * It then displays a list of `Review` cards. Reviews where `isAdminReview` is `true` are
      visually
      distinguished (e.g., with a gold border or a tag using `CardDefaults.cardColors`) and are
      always
      sorted to appear at the top of the list.
    * Each review card shows the review text, the endorse/avoid vote, and interactive buttons to let
      the current user verify the review.
    * Tapping the FAB is a **gated interaction**: an anonymous user is navigated to `AuthScreen`,
      while an authenticated user proceeds to `AddReviewScreen`. This logic is handled by the
      `onProtectedAction` helper function.
* **State Handling**:
    * The `PlaceDetailViewModel` would hold a `StateFlow<PlaceDetailUiState>`.
    * `PlaceDetailUiState.place`: A nullable `Place` object holding the details for the header.
    * `PlaceDetailUiState.reviews`: A `List<Review>` that populates the `LazyColumn`.
    * `PlaceDetailUiState.isLoading`: A `Boolean` to show a `CircularProgressIndicator` while the
      initial place and review data are being fetched.
* **Accessibility (`a11y`)**:
    * The `TopAppBar` title correctly identifies the screen's context.
    * The FAB must have a `contentDescription` like "Add a new review".
    * The verification buttons (`thumb_up`/`thumb_down`) on each review card must have content
      descriptions that include the review's text and the action, e.g., "Endorse review by [User]
      that
      says '[review text]'".

## 3. `AuthScreen` & `ProfileScreen`

* **Purpose**: To manage the user's authentication state, providing a clear path for anonymous users
  to register or log in, and for authenticated users to log out.
* **`AuthScreen`**:
    * A minimalist, centered form containing `OutlinedTextField` composables for email and password.
    * A toggle or button allows the user to switch the screen's mode between Login (
      `signInWithEmail`)
      and Sign Up (`signUpWithEmail`).
    * **State Handling**: The `AuthViewModel`'s `AuthUiState` holds the `email` and `password`
      strings
      from the text fields, an `isLoginMode` boolean for the toggle, an `isLoading` boolean to show
      a
      progress indicator on the submit button, and a nullable `error` string to display
      authentication
      failures.
* **`ProfileScreen`**:
    * This screen renders conditionally based on the user's authentication state, which is retrieved
      directly from `Firebase.auth.currentUser`.
    * **Anonymous State**: Displays text like "You are Browse anonymously" and a single button to
      navigate to the `AuthScreen`.
    * **Authenticated State**: Displays the user's email and a single "Sign Out" button that
      triggers
      `firebaseService.signOut()`.
* **Accessibility (`a11y`)**:
    * All `TextField` elements must have a corresponding `label` that describes the expected input.
    * The "Sign Out" button should be clearly marked, and if it leads to data loss, a confirmation
      dialog might be considered.

*(This same level of detail would be applied to all other screens like `AdminDashboardScreen`)*