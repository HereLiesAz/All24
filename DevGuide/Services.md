Part 4: The Divine Machinery - Services & Logic
I. The FirebaseService: The One True Conduit

All communication with the Firestore underworld is channeled through a single, monolithic service class: FirebaseService.kt. This is not a concession to modern, hyper-specialized architectural patterns. It is a theological statement. There is one god, and therefore one conduit to the divine.

This class is responsible for all CRUD (Create, Read, Update, Delete) operations. It translates the raw data from Firestore into the Kotlin data class models defined in Part 2. All functions that perform writes (suspend) are transactional where necessary to prevent the corruption of sacred texts. All functions that read data streams (Flow) provide real-time updates to the UI, making the app feel alive and responsive to the whispers of the database.

II. The ViewModels: The Minor Prophets

Each screen (or complex composable) that requires state management or business logic has a corresponding ViewModel. These are the minor prophets, each responsible for interpreting the divine will for a specific part of the mortal world.

Responsibilities:

Holding State: They expose a StateFlow of a UI-specific State data class (e.g., VibeUiState, PlaceDetailUiState). This is the single source of truth for their corresponding UI.

Invoking the Seance: They call methods on the FirebaseService to fetch or manipulate data.

Interpreting Prophecy: They contain the business logic to transform the raw data from the service into a state that the UI can mindlessly render. For example, the PlaceDetailViewModel is responsible for sorting reviews to place Admin posts at the top.

Handling Mortal Input: They expose public functions that the UI can call in response to user actions (e.g., conjureRecommendations(), verifyReview()).

III. The Navigation Graph: The Fates

The user's journey through the app is not a free-roaming exploration; it is a path pre-ordained by the AppNavigation.kt file. This is the loom of the Fates, defining every possible route and the arguments they require.

sealed class Screen: A simple, elegant construct that defines all possible destinations as typesafe objects. This prevents the mortal error of mistyping a route string. Routes that require parameters (e.g., place_detail/{placeId}) include a createRoute function to ensure correct path construction.

NavHost: The loom itself. It maps the Screen routes to the @Composable functions that render them.

Gated Navigation: Critical actions are wrapped in a helper function, onProtectedAction. This function checks the current user's state (isAnonymous). If the user is a ghost, it redirects them to the Screen.Auth route. If they are corporeal, it executes the intended action (e.g., navigating to Screen.SubmitPlace). This is the bouncer at the door of the VIP lounge.