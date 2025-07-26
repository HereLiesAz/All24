# Core User Flows

This document provides a detailed, step-by-step breakdown of the primary user-system interactions,
including error handling and sequence diagrams.

## 1. Flow: Generating Recommendations

This flow describes the process from a user's tap on the `VibeScreen` to the display of new
recommendations.

* **Sequence Diagram**:
    ```
    User        UI (VibeScreen)   ViewModel (VibeVM)   Service (FirebaseService)   Firestore DB
     |                |                    |                       |                    |
     |--- Tap Canvas ->|                    |                       |                    |
     |                |-- conjureRecomms() ->|                       |                    |
     |                |                    |---- getPlaces() ------>|                    |
     |                |                    |                       |---- GET /places -->|
     |                |                    |                       |<--- List<Place> ---|
     |                |                    |<---- List<Place> ------|                    |
     |                |                    |-----------------------.                    |
     |                |                    | (Shuffle, take 3)     |                    |
     |                |                    | Update StateFlow      |                    |
     |                |<-- Recompose w/ Recs|-----------------------'                    |
     |<-- See Recs ---|                    |                       |                    |
    ```
* **Step-by-Step Breakdown**:
    1. [cite_start]**UI Event (Invocation)**: The user taps anywhere on the `VibeScreen`'s
       `Canvas`[cite: 465].
    2. [cite_start]**ViewModel Call**: The `onClick` lambda in the UI calls the
       `VibeViewModel.conjureRecommendations()` function[cite: 465].
    3. [cite_start]**Service Call**: The `VibeViewModel` launches a coroutine in `viewModelScope`
       and
       calls the `suspend` function `FirebaseService.getPlaces()`[cite: 466].
    4. **Data Retrieval**: The `FirebaseService` performs a `db.collection("places").get().await()`,
       which returns a `QuerySnapshot`. [cite_start]The service maps the documents to a
       `List<Place>`
       and returns it[cite: 467, 65].
    5. **Logic**: The `VibeViewModel` receives the `List<Place>`. [cite_start]It shuffles the list,
       selects a random subset (up to three), and updates its `_uiState` `MutableStateFlow` with the
       new list, setting `showRecommendations = true`[cite: 468, 469, 183].
    6. **UI Update**: The `VibeScreen`, collecting the `uiState` `StateFlow`,
       recomposes. [cite_start]
       The `AnimatedVisibility` composable animates the recommendation panel into
       view[cite: 470, 410].
* **Error Scenarios & Edge Cases**:
    * **No Places Available**: If `firebaseService.getPlaces()` returns an empty list, the
      `VibeViewModel` should not change the `showRecommendations` flag, effectively doing nothing. A
      log message should be recorded.
    * **Firestore Exception**: If `getPlaces()` throws an exception (e.g.,
      `FirebaseFirestoreException` due to being offline), the `catch` block in the `VibeViewModel`
      will capture it. [cite_start]The `UiState`'s `error` property should be updated, which could
      trigger a `Snackbar` to inform the user of the network issue[cite: 417, 418, 419].

## 2. Flow: Verifying an Existing Review

This flow details how a user's endorsement of a review is recorded in real-time.

* **Sequence Diagram**:
    ```
    User        UI (PlaceDetail)   ViewModel (PlaceDetailVM) Service (FirebaseService)   Firestore DB
     |                 |                      |                       |                    |
     |--- Tap Endorse ->|                      |                       |                    |
     |                 |--- verifyReview() --->|                       |                    |
     |                 |                      |--- verifyReview() ---->|                    |
     |                 |                      |                       |---- TRANSACTION -->|
     |                 |                      |                       | (arrayUnion/Remove)|
     |                 |                      |                       |<---- SUCCESS ------|
     |                 |                      |<----------------------|                    |
     |                 |                      |                       |                    |
     |                 |<------------------------------------------------------------------| (Realtime Push)
     |<-- See Updated --| (Recompose w/ Count)  |                       |                    |
    ```
* **Step-by-Step Breakdown**:
    1. [cite_start]**UI Event (Declaration)**: On the `PlaceDetailScreen`, a user taps the "Endorse"
       `IconButton` on a review card[cite: 471].
    2. [cite_start]**ViewModel Call**: The `onClick` lambda calls
       `PlaceDetailViewModel.verifyReview(reviewId, "endorse")`[cite: 472].
    3. [cite_start]**Service Call**: The `PlaceDetailViewModel` calls
       `FirebaseService.verifyReview(reviewId, vote)`[cite: 473].
    4. **Database Transaction**: The `FirebaseService` executes a `db.runTransaction`. [cite_start]
       It
       atomically adds the `userId` to the `endorsedBy` array via `FieldValue.arrayUnion` and
       removes
       it from the `avoidedBy` array via `FieldValue.arrayRemove`[cite: 69, 474].
    5. **Real-time Push (Echo)**: The `PlaceDetailViewModel` is observing a `Flow` of reviews via
       `getReviewsForPlaceStream`. [cite_start]When the transaction completes, Firestore's listener
       pushes the updated document to the client[cite: 475].
    6. [cite_start]**UI Update (Confirmation)**: The `Flow` emits the new list, and the
       `PlaceDetailScreen` recomposes, showing the updated verification count on the review card
       instantly[cite: 476].
* **Error Scenarios & Edge Cases**:
    * **User Not Authenticated**: The `verifyReview` function in the `FirebaseService` first checks
      `currentUser?.uid`. [cite_start]If it's null, it throws an
      `Exception("User not authenticated")`, which is caught by the ViewModel and propagated to the
      UI
      as an error state[cite: 69].
    * **Transaction Failure**: If the transaction fails after multiple retries (e.g., heavy
      contention), Firestore will throw an exception. This is caught by the ViewModel and displayed
      as
      an error to the user, who may need to try again.

*(This same level of detail would be applied to the Submission and Approval flows)*