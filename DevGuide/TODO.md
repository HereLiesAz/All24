### Phase 1: The Divine Machinery (Admin & Core Logic)

- **Admin Workflow: The Judgment Seat**
    - [ ] **`AdminSubmissionDetailScreen` Implementation:**
        - [ ] Create the screen composable, accepting a `submissionId` as a navigation argument.
        - [ ] Implement a `AdminSubmissionDetailViewModel` to fetch the specific `Submission` document from Firestore using the `submissionId`.
        - [ ] The UI must display all fields from the `Submission` object as non-editable `Text` composables.
        - [ ] The `address` field must be displayed as plain text. There will be no map. The admin is expected to possess the omniscience to locate it themselves.
        - [ ] **The Bestowal of Property:**
            - [ ] The `ViewModel` must fetch a list of all users where `role == 'business'`.
            - [ ] The UI must contain a `DropdownMenu` (or similar selector) populated with the emails of these business-role users. This is the "Assign Owner" control.
            - [ ] The admin must be able to select a business user from this list. The selected user's `uid` will be stored in the `ViewModel`'s state.
    - [ ] **`AdminSubmissionDetailScreen` Verdict Logic:**
        - [ ] Create an "Approve" `Button`. Its `onClick` lambda will trigger `viewModel.approveSubmission()`.
        - [ ] The `approveSubmission` function must perform the following `FirebaseService` call:
            - A `WriteBatch` operation that atomically:
                1. Creates a new document in the `places` collection, transcribing the data from the `Submission`.
                2. Writes the selected business user's `uid` to the new `Place`'s `ownerId` field.
                3. Deletes the original document from the `place_submissions` collection.
        - [ ] Create a "Deny" `Button`. Its `onClick` lambda will trigger `viewModel.denySubmission()`.
        - [ ] The `denySubmission` function calls `firebaseService.denySubmission()`, which is a simple delete operation on the `place_submissions` document.
        - [ ] Both approval and denial actions must update the `ViewModel` state to show a loading indicator (`isLoading`) and, upon completion, navigate back to the `AdminDashboardScreen`.

- **Admin Workflow: The Ordination Chamber**
    - [ ] **`AdminUserManagementScreen` Implementation:**
        - [ ] Create the screen composable.
        - [ ] Implement a `AdminUserManagementViewModel` that streams the entire `users` collection from Firestore.
        - [ ] The UI will be a `LazyColumn` displaying a `Card` for each `UserModel`. Each card must show the user's `email` and their current `role`.
    - [ ] **`AdminUserManagementScreen` Role-Change Logic:**
        - [ ] Tapping a user `Card` must open an `AlertDialog`.
        - [ ] The dialog will display the user's email and a set of `RadioButton`s or a `DropdownMenu` to select a new role ('user', 'business', 'admin').
        - [ ] A "Confirm" button in the dialog will trigger a `viewModel.updateUserRole(uid, newRole)` function.
        - [ ] The `updateUserRole` function calls a new `FirebaseService` method that performs an `update` operation on the specified user document, changing the value of the `role` field.

### Phase 2: The Mortal Realm (Business & User Features)

- **Business Owner Workflow: The Counting House & Scriptorium**
    - [ ] **`BusinessDashboardScreen` Implementation:**
        - [ ] The `BusinessDashboardViewModel` must stream the `places` collection, using a `where("ownerId", "==", currentUser.uid)` query.
        - [ ] The UI will be a `LazyColumn` of `Place` items owned by the current user.
        - [ ] Tapping a `Place` item must navigate to the `EditPlaceScreen`, passing the `placeId`.
    - [ ] **`EditPlaceScreen` Implementation:**
        - [ ] The screen accepts a `placeId` navigation argument.
        - [ ] The `EditPlaceViewModel` fetches the specific `Place` document.
        - [ ] The UI displays the `name` and `description` in `OutlinedTextField` composables, bound to the `ViewModel`'s state.
        - [ ] A "Save Changes" `Button` triggers `viewModel.saveChanges()`, which calls `firebaseService.updatePlace()` to update the document in Firestore. Upon success, it navigates back to the dashboard.

- **UI & UX Refinements: Polishing the Ghost**
    - [ ] **`VibeScreen` Recommendation Animation:**
        - [ ] The recommendation list must be wrapped in an `AnimatedVisibility` composable.
        - [ ] The `visible` property will be bound to the `VibeUiState.showRecommendations` boolean.
        - [ ] The `enter` animation will be `slideInVertically(initialOffsetY = { it }) + fadeIn()`.
        - [ ] The `exit` animation will be `slideOutVertically(targetOffsetY = { it }) + fadeOut()`.
    - [ ] **`VibeScreen` Conditional Icon Logic:**
        - [ ] The bottom-left `VibeNavButton` must be a conditional composable.
        - [ ] It will observe the `VibeViewModel.userRole` `StateFlow`.
        - [ ] A `when` statement will render the button with the `Icons.Default.Business` icon and `Screen.BusinessDashboard` route if `role == 'business'`.
        - [ ] Otherwise, it will render the `Icons.Default.AddBusiness` icon and `Screen.SubmitPlace` route.
    - [ ] **Universal Error Prophecy:**
        - [ ] Every `ViewModel` `UiState` data class must contain a nullable `error: String?` property.
        - [ ] Every `try/catch` block that calls a `FirebaseService` write operation must update this `error` property in the `catch` block.
        - [ ] Every screen must contain a `SnackbarHost` and a `LaunchedEffect` that observes the `error` property. If the error is not null, it will show a `Snackbar` with the error message.