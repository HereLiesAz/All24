# Application Architecture

The All24 application is constructed using a modern, hybrid architecture designed for
maintainability, testability, and the use of the best tool for each specific UI task. It combines
the declarative power of Jetpack Compose with the targeted strengths of the traditional Android View
system.

## 1. Hybrid UI Architecture (Compose + Views)

While the application's overall structure, navigation, and static screens are built with Jetpack
Compose, the core Browse experience utilizes a critical component from the Material Components for
Android library (the View system):

* **`com.google.android.material.carousel.CarouselLayoutManager`**

This decision was made because `CarouselLayoutManager` provides the exact, physics-based,
multi-browse carousel behavior specified by the Material 3 Expressive guidelines. To integrate this
View-based component into the Compose UI, the application uses the **`AndroidView`** composable.

This hybrid approach allows the app to benefit from the rapid development and declarative nature of
Compose while still leveraging powerful, pre-built components from the mature View ecosystem.

## 2. Data Flow Overview

The standard data flow for a read operation is as follows:

**Data Source (Mock Service/Firestore) -> `ViewModel` -> `StateFlow` -> UI
Composable -> `AndroidView` -> `RecyclerView.Adapter`**

For a write operation initiated from a Compose screen, the flow is:

**UI Composable -> `ViewModel` -> Service -> Data Source**

This unidirectional flow ensures that the state of the application is predictable and easy to debug.

## 3. Detailed Component Breakdown

### ViewModels (Presentation Logic Layer)

Each screen or complex, stateful composable has a dedicated `ViewModel` that extends
`androidx.lifecycle.ViewModel`. They expose UI state via `StateFlow` and are responsible for calling
services to fetch or mutate data.

### Navigation Layer (`AppNavigation.kt`)

The navigation layer is built with Navigation Compose and is responsible for defining all paths
through the application.

* **`SharedTransitionLayout`:** The entire `NavHost` is wrapped in `SharedTransitionLayout` to
  enable choreographed, shared element transitions between composable screens, a key feature of the
  M3 Expressive motion system.
* **Scope Passing:** The `SharedTransitionScope` and `AnimatedVisibilityScope` are passed down from
  the navigation graph to the screen composables that participate in transitions.

### Carousel Integration (`MainCarouselScreen.kt`)

The main screen uses a `VerticalPager` (Compose) to manage the different content categories. Each
page of this pager contains an `AndroidView` composable.

* **`AndroidView` Factory:** Inside the `factory` block of the `AndroidView`, the app inflates an
  XML layout containing a `RecyclerView`. It then programmatically creates an instance of
  `CarouselLayoutManager` and a `CarouselAdapter` and attaches them to the `RecyclerView`.
* **Data Binding:** The `ViewModel`'s state is passed to the `CarouselAdapter` to populate the
  `RecyclerView` with the correct items for that category.