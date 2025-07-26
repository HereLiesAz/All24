# Development Roadmap

This document outlines pending development tasks, categorized by feature set and priority.

## Phase 1: Admin Workflow Enhancements

This phase focuses on completing the core administrative toolset for content and user management.

* **Task: Complete `AdminSubmissionDetailScreen` Logic**
    * **Objective:** To provide admins with the full capability to approve or deny submissions and
      assign ownership.
    * **Requirements:**
        1. [cite_start]The `AdminSubmissionDetailViewModel` must be implemented to accept a
           `submissionId` from `SavedStateHandle` and use it to fetch the corresponding `Submission`
           document from Firestore[cite: 380, 116].
        2. [cite_start]The screen's UI must render all fields from the fetched `Submission` object
           as read-only `Text` composables[cite: 381]. [cite_start]The `address` field must be plain
           text, requiring manual verification by the admin[cite: 382].
        3. [cite_start]A new `FirebaseService` call must be added to fetch all users where
           `role == 'business'`[cite: 385].
        4. The UI must include a `DropdownMenu` or similar selector populated with the emails of
           these business users. [cite_start]This control allows the admin to assign ownership of
           the new `Place` before approval[cite: 385, 386]. [cite_start]The selected business user's
           `uid` must be stored in the ViewModel's state[cite: 387].
        5. The "Approve" button's `onClick` will trigger
           `viewModel.approveSubmission()`. [cite_start]This function must call a `FirebaseService`
           method that performs an atomic `WriteBatch` to create a new document in the `places`
           collection (including the selected `ownerId`) and delete the original document from
           `place_submissions`[cite: 389, 390, 391].
        6. The "Deny" button's `onClick` will trigger `viewModel.denySubmission()`. [cite_start]This
           calls a service method that performs a simple delete operation on the `place_submissions`
           document[cite: 392].
        7. [cite_start]Upon successful approval or denial, the `ViewModel` must trigger a navigation
           action to return to the `AdminDashboardScreen`[cite: 393, 103].

* **Task: Implement User Role Management**
    * **Objective:** To allow admins to change the role of any user in the system.
    * **Requirements:**
        1. [cite_start]Create a new `AdminUserManagementScreen` and its corresponding
           `AdminUserManagementViewModel`[cite: 395].
        2. [cite_start]The ViewModel must stream the entire `users` collection from
           Firestore[cite: 396].
        3. [cite_start]The UI will be a `LazyColumn` of `Card`s, with each card displaying a user's
           `email` and their current `role`[cite: 397].
        4. [cite_start]Tapping a user card must open an `AlertDialog`[cite: 399].
        5. [cite_start]The dialog must contain a set of `RadioButton`s or a dropdown to select a new
           role from the available options (`user`, `business`, `admin`)[cite: 400].
        6. [cite_start]A "Confirm" button in the dialog will trigger a ViewModel function
           `updateUserRole(uid, newRole)`[cite: 401].
        7. [cite_start]This function will call a new `FirebaseService` method that performs an
           `update` operation on the specified user document, changing only the value of the `role`
           field[cite: 402].

## Phase 2: User-Facing Features & Refinements

This phase focuses on building out features for the `business` role and improving the overall user
experience.

* **Task: Implement Business Owner Workflow**
    * **Objective:** To allow users with the `business` role to manage the places they own.
    * **Requirements:**
        1. [cite_start]The `BusinessDashboardViewModel` must be implemented to stream the `places`
           collection using a `where("ownerId", "==", currentUser.uid)` query to fetch only the
           places owned by the currently logged-in user[cite: 403, 121].
        2. [cite_start]The `BusinessDashboardScreen` UI will be a `LazyColumn` of the owned `Place`
           items[cite: 404].
        3. [cite_start]Tapping a `Place` item in the dashboard must navigate to the
           `EditPlaceScreen`, passing the `placeId` as an argument[cite: 405].
        4. [cite_start]The `EditPlaceScreen` and its `EditPlaceViewModel` must fetch the specified
           `Place` document using the passed `placeId`[cite: 406, 407].
        5. [cite_start]The UI must display the `name` and `description` of the place in
           `OutlinedTextField`s, with their values bound to the ViewModel's state[cite: 408].
        6. [cite_start]A "Save Changes" button must trigger a `viewModel.saveChanges()` function,
           which calls `firebaseService.updatePlace()` to persist the changes to Firestore and then
           navigates back to the dashboard[cite: 409, 131].

* **Task: Refine UI & UX**
    * **Objective:** To add polish and more dynamic behavior to the UI.
    * **Requirements:**
        1. **VibeScreen Animation:** The recommendation list must be wrapped in an
           `AnimatedVisibility` composable. [cite_start]The `visible` property must be bound to the
           `VibeUiState.showRecommendations` boolean[cite: 410, 411]. [cite_start]The `enter`
           animation must be `slideInVertically` + `fadeIn`, and the `exit` animation must be
           `slideOutVertically` + `fadeOut`[cite: 412].
        2. **VibeScreen Conditional Navigation:** The bottom-left `VibeNavButton` must be
           implemented as a conditional composable. [cite_start]It will observe the user's role from
           a `StateFlow` in the `VibeViewModel`[cite: 414]. [cite_start]A `when` statement will
           determine its icon and navigation route: `Icons.Default.Business` and
           `Screen.BusinessDashboard` for the `business` role, and `Icons.Default.AddBusiness` and
           `Screen.SubmitPlace` otherwise[cite: 415].
        3. [cite_start]**Universal Error Handling:** Every `UiState` data class must include a
           nullable `error: String?` property[cite: 417]. [cite_start]Every `try/catch` block
           surrounding a `FirebaseService` call in the ViewModels must update this `error` property
           in the `catch` block[cite: 418]. [cite_start]Every screen must contain a `SnackbarHost`
           and a `LaunchedEffect` that observes the `error` property and shows a `Snackbar` with the
           message if it is not null[cite: 419].