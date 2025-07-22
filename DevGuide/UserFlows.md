Part 5: The Sacred Rituals - Core User Flows
I. The Ritual of Revelation (Conjuring Recommendations)

The Invocation: The User taps the VibeScreen.

The Seance: The VibeScreen UI calls viewModel.conjureRecommendations().

The Prayer: The VibeViewModel calls firebaseService.getPlaces().

The Whisper: The FirebaseService fetches all documents from the places collection.

The Interpretation: The VibeViewModel receives the list of Place objects. It shuffles the list and selects a maximum of three at random.

The Prophecy: The VibeViewModel updates its VibeUiState, populating the recommendations list and setting showRecommendations to true.

The Vision: The VibeScreen observes the state change and animates the recommendation panel into view, displaying the three chosen sanctuaries.

II. The Ritual of Judgment (Verifying a Review)

The Declaration: The User, on the PlaceDetailScreen, taps the "Endorse" or "Avoid" button on a Review card.

The Seance: The UI calls viewModel.verifyReview(reviewId, vote).

The Prayer: The PlaceDetailViewModel calls firebaseService.verifyReview(reviewId, vote).

The Divine Act: The FirebaseService initiates a Firestore transaction. It atomically adds the user's uid to the appropriate array (endorsedBy or avoidedBy) and removes it from the other, preventing a soul from serving two gods.

The Echo: Because the PlaceDetailScreen is observing a real-time Flow of reviews from Firestore, the database change is automatically pushed to the app.

The New Scripture: The PlaceDetailViewModel receives the updated list of reviews.

The Confirmation: The PlaceDetailScreen recomposes, showing the updated verification count and highlighting the user's selected vote. The act is instantly and permanently recorded in the digital ether.

III. The Ritual of Creation (Submitting a Place)

The Calling: A corporeal User taps the "Submit a Place" icon on the VibeScreen.

The Pilgrimage: The app navigates to the SubmitPlaceScreen.

The Offering: The User fills out the form and taps "Submit for Review."

The Seance: The UI calls viewModel.submit().

The Prayer: The SubmitPlaceViewModel calls firebaseService.submitPlace(...) with the form data.

The Whisper to Purgatory: The FirebaseService creates a new document in the place_submissions collection.

The Return: Upon success, the app navigates back, and a SnackBar confirms that their prayer has been heard and is now awaiting judgment.

IV. The Ritual of Ordination (Approving a Submission)

The Divine Gaze: An Admin user navigates to the AdminDashboardScreen. They see a list of pending submissions.

The Scrutiny: The Admin taps a submission, navigating to the AdminSubmissionDetailScreen.

The Judgment: The Admin reviews the details and taps "Approve."

The Divine Act: The UI calls a viewModel.approve(submission) function.

The Creation and Annihilation: The AdminDashboardViewModel calls firebaseService.approveSubmission(submission). The service performs a batch write: it creates a new Place document in the places collection and simultaneously deletes the original Submission document from place_submissions. The prayer is answered and consumed in the same divine breath.

The Echo: The AdminDashboardScreen, observing the submissions stream, automatically updates to show one less pending item. The new Place is now part of the world, ready to be discovered by the Vibe.