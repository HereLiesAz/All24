# Application Architecture

The All24 application is constructed using a modern, reactive architecture designed for
maintainability, testability, and separation of concerns. The architecture can be understood as a
series of layers, each with distinct responsibilities, facilitating a unidirectional data flow.

## 1. Data Flow Overview

The standard data flow for a read operation is as follows:

**Firestore -> `FirebaseService` -> `ViewModel` -> `StateFlow` -> UI Composable**

For a write operation, the flow is reversed:

**UI Composable -> `ViewModel` -> `FirebaseService` -> Firestore**

This unidirectional flow ensures that the state of the application is predictable and easy to debug.
The UI only ever reacts to state changes from the ViewModel; it never modifies data sources
directly.

*(ASCII Art Data Flow Diagram would be inserted here)*

## 2. Detailed Component Breakdown

### `FirebaseService.kt` (Repository Layer)

This is a singleton class that serves as the exclusive intermediary between the application and the
Firestore database. It implements the Repository design pattern.

* **Singleton Implementation:** The choice of a Kotlin `object` provides a simple, thread-safe
  singleton without requiring manual implementation of a singleton pattern. This ensures that only
  one instance of the service exists, preventing multiple database connections and potential
  listener leaks.
* **Interface Abstraction (Deliberate Omission):** For this project's current scale, an interface
  for `FirebaseService` has been deliberately omitted to reduce boilerplate. The service is treated
  as a concrete final implementation. In a larger project or one requiring more complex test fakes,
  an interface (`FirebaseServiceContract`) would be introduced, and ViewModels would depend on the
  interface rather than the concrete class, facilitating easier substitution in tests.
* **Coroutine & Flow Integration:**
    * **Suspend Functions:** All one-shot database operations (e.g., `get()` a single document,
      `set()` a new document) are encapsulated in `suspend` functions. This makes them main-safe,
      meaning they can be called from the main thread (within a coroutine) without blocking it, as
      the
      `kotlinx-coroutines-play-services` library handles moving the work to a background thread.
    * **`Flow<T>` for Real-Time Data:** For data that needs to be observed in real-time (e.g., a
      list
      of reviews for a place), the service exposes a `kotlinx.coroutines.flow.Flow`. It uses the
      `callbackFlow` builder to wrap Firestore's `addSnapshotListener` callback-based API into a
      cold,
      cancellable Flow. This is the cornerstone of the app's reactive nature, as UI updates happen
      automatically when data changes in the backend.

### ViewModels (Presentation Logic Layer)

Each screen or complex, stateful composable has a dedicated `ViewModel` that extends
`androidx.lifecycle.ViewModel`.

* **State Management with `StateFlow`:**
    1. **State Data Class:** A screen-specific `data class` (e.g., `AdminSubmissionDetailState`) is
       defined to immutably represent the entirety of the UI's state at any given moment. This
       includes lists of data, loading booleans, error messages, and user input.
    2. **Internal `MutableStateFlow`:** The ViewModel holds a
       `private val _uiState = MutableStateFlow(InitialState)`.
    3. **External `StateFlow`:** It exposes a public, immutable
       `val uiState = _uiState.asStateFlow()`. The UI collects this Flow using `collectAsState()`,
       automatically triggering recomposition whenever a new state is emitted.
* **`viewModelScope`:** All coroutines initiated by the ViewModel are launched in `viewModelScope`.
  This is critical as it automatically cancels the coroutines if the ViewModel is cleared (e.g.,
  when the screen is permanently destroyed), preventing memory leaks and unnecessary background
  work.
* **`SavedStateHandle`:** For ViewModels that need to survive process death (e.g.,
  `AdminSubmissionDetailViewModel` which depends on a `submissionId` passed via navigation), the
  `SavedStateHandle` is used. The `submissionId` is retrieved from the handle, ensuring that if the
  app process is killed by the OS and restored, the ViewModel can re-fetch its necessary data
  without losing context.

### Navigation Layer (`AppNavigation.kt`)

The navigation layer is responsible for defining all possible paths through the application and
managing the navigation stack.

* **Sealed Class for Type-Safety:** The use of a `sealed class Screen` is a key architectural
  choice. It turns abstract route strings into compile-time-checked objects, eliminating a common
  source of runtime navigation errors. Each destination is an `object` within the sealed class.
* **Argument Handling:** For routes that require arguments (e.g., `place_detail/{placeId}`), the
  corresponding object in the sealed class includes a `createRoute(arg: String): String` function.
  This function is responsible for building the correctly formatted route string, abstracting the
  URL-like path construction away from the call site and ensuring consistency.
* **Nested Navigation Graphs (Future Consideration):** While the current implementation uses a
  single, flat `NavHost`, the architecture is prepared for future expansion. For features with
  multiple related screens (e.g., a more complex business owner dashboard), a nested navigation
  graph could be introduced by calling the `navigation()` builder within the main `NavHost` builder,
  creating a self-contained flow.

*(... and so on, continuing to add exhaustive detail to each section.)*