# Comprehensive Testing Strategy

## 1. Introduction & Testing Philosophy

This document outlines the comprehensive testing strategy for the All24 application, designed to
ensure code quality, stability, and maintainability. The strategy is based on the "Testing Pyramid"
concept, which advocates for a large base of fast, isolated unit tests, a smaller layer of more
complex integration tests, and a minimal set of slow, end-to-end UI tests.

This approach ensures rapid feedback during development (from unit tests), validates interactions
between components (from integration tests), and guarantees that critical user flows function as
expected from the user's perspective (from UI tests).

## 2. Unit Testing

Unit tests are the foundation of the testing pyramid. They are responsible for testing individual
classes or functions in complete isolation from their dependencies (like the Android framework,
Firebase services, etc.).

* **Scope:** The primary candidates for unit testing in this project are the ViewModels. Each
  ViewModel's public methods, state transformations, and interactions with its dependencies (
  specifically `FirebaseService`) should be thoroughly tested.
* **Tools:**
    * **JUnit 4:** The standard test runner for local JVM tests.
    * **Turbine:** A small library for testing `kotlinx.coroutines.flow.Flow` objects, essential for
      verifying `StateFlow` emissions from the ViewModels.
    * **MockK:** A powerful mocking library for Kotlin, used to create mock objects for dependencies
      like `FirebaseService`. This allows us to define the behavior of the service for a given test
      and verify that the ViewModel interacts with it as expected.
    * **Main-Dispatcher-Rule:** A custom JUnit rule to replace the main coroutine dispatcher in
      tests, allowing for immediate execution of coroutines launched on `viewModelScope`.

* **Detailed Example: Testing `SubmitPlaceViewModel`**

    ```kotlin
    // Located in: app/src/test/java/com/hereliesaz/all24/ui/screens/submit_place/SubmitPlaceViewModelTest.kt

    import app.cash.turbine.test
    import com.hereliesaz.all24.services.FirebaseService
    import io.mockk.coEvery
    import io.mockk.coVerify
    import io.mockk.mockk
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.ExperimentalCoroutinesApi
    import kotlinx.coroutines.test.StandardTestDispatcher
    import kotlinx.coroutines.test.resetMain
    import kotlinx.coroutines.test.runTest
    import kotlinx.coroutines.test.setMain
    import org.junit.After
    import org.junit.Assert.assertEquals
    import org.junit.Assert.assertFalse
    import org.junit.Assert.assertNull
    import org.junit.Assert.assertTrue
    import org.junit.Before
    import org.junit.Test

    @ExperimentalCoroutinesApi
    class SubmitPlaceViewModelTest {

        // The dependency to be mocked
        private lateinit var firebaseService: FirebaseService
        // The class under test
        private lateinit var viewModel: SubmitPlaceViewModel

        private val testDispatcher = StandardTestDispatcher()

        @Before
        fun setUp() {
            // Set the main dispatcher to a test dispatcher
            Dispatchers.setMain(testDispatcher)
            // Create a mock instance of the FirebaseService
            firebaseService = mockk()
            // Instantiate the ViewModel with the mock service
            viewModel = SubmitPlaceViewModel(firebaseService) // Assuming constructor injection
        }

        @After
        fun tearDown() {
            // Reset the main dispatcher after the test
            Dispatchers.resetMain()
        }

        @Test
        fun `submit with blank name fails and sets error state`() = runTest {
            // When the submit function is called with a blank name
            viewModel.onDescriptionChange("A valid description")
            viewModel.onAddressChange("A valid address")
            viewModel.submit()

            // Then assert that the UI state reflects the error
            val expectedState = SubmitPlaceUiState(
                description = "A valid description",
                address = "A valid address",
                error = "All fields are required."
            )
            assertEquals(expectedState, viewModel.uiState.value)

            // And verify that the service was never called
            coVerify(exactly = 0) { firebaseService.submitPlace(any(), any(), any(), any()) }
        }

        @Test
        fun `submit with valid data calls service and sets success state`() = runTest {
            // Given a successful response from the service
            coEvery { firebaseService.submitPlace(any(), any(), any(), any()) } returns Unit

            // When valid data is entered and submit is called
            viewModel.onNameChange("Test Place")
            viewModel.onDescriptionChange("Test Description")
            viewModel.onAddressChange("123 Test St")
            viewModel.submit()

            // Then use Turbine to test the sequence of state emissions
            viewModel.uiState.test {
                // Initial state upon collection
                var item = awaitItem()
                assertTrue(item.name.isNotEmpty())

                // State during submission (isLoading = true)
                item = awaitItem()
                assertTrue(item.isLoading)
                assertNull(item.error)

                // Final state after success (isSuccess = true, isLoading = false)
                item = awaitItem()
                assertFalse(item.isLoading)
                assertTrue(item.isSuccess)

                // Cancel the flow to prevent leaks
                cancelAndIgnoreRemainingEvents()
            }

            // And verify that the service was called exactly once with the correct data
            coVerify(exactly = 1) {
                firebaseService.submitPlace(
                    name = "Test Place",
                    description = "Test Description",
                    address = "123 Test St",
                    category = "bar" // Default category
                )
            }
        }

        @Test
        fun `submit when service throws exception sets error state`() = runTest {
            // Given the service will throw an exception
            val exception = Exception("Network error")
            coEvery { firebaseService.submitPlace(any(), any(), any(), any()) } throws exception

            // When submit is called with valid data
            viewModel.onNameChange("Test Place")
            viewModel.onDescriptionChange("Test Description")
            viewModel.onAddressChange("123 Test St")
            viewModel.submit()

            // Then assert that the final UI state reflects the error message from the exception
            val finalState = viewModel.uiState.value
            assertFalse(finalState.isLoading)
            assertFalse(finalState.isSuccess)
            assertEquals("Network error", finalState.error)
        }
    }
    ```

## 3. Integration Testing

Integration tests verify the interaction between different parts of the application, specifically
the `FirebaseService` and the live Firebase backend (via the Firebase Emulator Suite).

* **Scope:** These tests will focus exclusively on the `FirebaseService.kt` class. They will confirm
  that the service correctly serializes data to Firestore and deserializes data from it.
* **Tools:**
    * **Firebase Emulator Suite:** A local-only version of Firebase services. The Firestore Emulator
      allows us to run tests against a real, but local, Firestore instance, providing high-fidelity
      testing without incurring costs or polluting a production database. The emulator must be
      running before these tests are executed.
    * **Android Test Runner (`AndroidJUnit4`):** These tests require an Android context and will run
      on an emulator or physical device.
* **Setup:**
    1. Before the tests run, the client `Firebase.firestore` must be pointed to the local emulator
       host and port (e.g., `10.0.2.2:8080`).
    2. After each test, the emulator's data should be cleared to ensure tests are hermetic and do
       not influence one another.

## 4. UI Testing (End-to-End)

UI tests are at the top of the pyramid. They simulate a real user's journey through the application,
ensuring that the UI, ViewModels, Service, and backend all work together correctly.

* **Scope:** Test the "happy path" of critical user flows. For All24, this includes:
    * The authentication flow (anonymous -> login -> authenticated).
    * The place submission flow.
    * Navigation between key screens.
* **Tools:**
    * **Espresso:** For interacting with UI components and making assertions.
    * **UI Automator:** For interactions that may span across different apps (less common in this
      project but available).
    * **Jetpack Compose Test APIs:** `createAndroidComposeRule` provides access to the
      `ComposeTestRule`, which is used to find composables, perform actions (clicks, text input),
      and assert their state.
    * **Hilt/Dagger for Dependency Injection (Recommended):** For a testable app, a dependency
      injection framework is recommended to replace real dependencies (like `FirebaseService`) with
      fake or mock versions in UI tests. This allows UI tests to run without a live network
      connection, making them faster and more reliable.

---

### **File: `DevGuide/BuildConfiguration.md`**

```markdown
# Build Configuration and Dependency Management

## 1. Overview

This document provides a detailed explanation of the project's build system, which is based on Gradle with Kotlin DSL (`.kts` files). It covers the structure of the build scripts, the dependency management strategy using a Version Catalog (`libs.versions.toml`), and the purpose of each key configuration.

## 2. Gradle Build Scripts

The project utilizes a modern Gradle setup with version catalogs and Kotlin scripting.

### `settings.gradle.kts`

* **Purpose:** This script is executed during the initialization phase of a Gradle build. It defines which modules are included in the build.
* **Key Sections:**
    * `pluginManagement`: Configures repositories for Gradle plugins themselves (e.g., `google()`, `mavenCentral()`).
    * `dependencyResolutionManagement`: Configures repositories for project dependencies and enables the version catalog feature by declaring the use of the `libs.versions.toml` file.
    * `include(":app", ":DevGuide")`: Declares the modules that are part of the project build.

### `build.gradle.kts` (Project-level)

* **Purpose:** This top-level build script defines configurations that are common to all modules in the project.
* **Key Sections:**
    * `plugins`: Applies plugins using aliases defined in `libs.versions.toml`. This includes setting up the Android Application plugin, Kotlin Android plugin, and Google Services plugin, but with `apply false`, meaning they are made available to sub-modules but not applied at the root level.

### `app/build.gradle.kts` (App-level)

* **Purpose:** This is the most critical build script, defining the specific build configuration and dependencies for the main `app` module.
* **Detailed Breakdown:**
    * **`plugins` block:** Applies the necessary plugins for an Android application using Compose and Firebase.
        * `id("com.android.application")`: Identifies this module as the main application.
        * `id("org.jetbrains.kotlin.android")`: Enables Kotlin support for Android.
        * `id("com.google.gms.google-services")`: Processes the `google-services.json` file to add Firebase configurations to the app.
    * **`android` block:** This extensive block configures all Android-specific build parameters.
        * [cite_start]`signingConfigs`: Configures the keystore used to sign the debug APK[cite: 40]. The configuration points to a local `.jks` file and includes credentials. For a production build, a separate `release` signing config would be added and credentials would be loaded securely (e.g., from environment variables or a secure properties file) rather than being hardcoded.
        * [cite_start]`namespace`: The unique application package name used in the generated `R` class[cite: 41].
        * [cite_start]`compileSdk`: The API level the app is compiled against (currently 36)[cite: 41].
        * `defaultConfig`:
            * [cite_start]`applicationId`: The unique ID for publishing to the Play Store[cite: 41].
            * [cite_start]`minSdk` / `targetSdk`: Defines the minimum and target Android API levels the app supports[cite: 41].
            * [cite_start]`versionCode` / `versionName`: Internal and public version identifiers for the app[cite: 41].
            * [cite_start]`testInstrumentationRunner`: Specifies the test runner for instrumented tests[cite: 41].
            * [cite_start]`vectorDrawables.useSupportLibrary = true`: Enables support for vector drawables on pre-Lollipop devices[cite: 42].
        * `buildTypes`: Defines build variants. [cite_start]The `release` block configures code shrinking (`isMinifyEnabled`) and ProGuard rules[cite: 43].
        * [cite_start]`compileOptions`: Sets the Java language compatibility to version 17[cite: 43].
        * [cite_start]`kotlinOptions`: Sets the JVM target for Kotlin to version 17[cite: 43].
        * [cite_start]`buildFeatures.compose = true`: Enables Jetpack Compose for the project[cite: 43].
        * [cite_start]`composeOptions.kotlinCompilerExtensionVersion`: Specifies the version of the Kotlin compiler plugin for Compose[cite: 43].
        * [cite_start]`packagingOptions`: Excludes certain meta-inf files to prevent build conflicts from duplicate files in dependencies[cite: 44].

## 3. Dependency Management with Version Catalog

[cite_start]The project uses a Gradle Version Catalog, defined in `gradle/libs.versions.toml`[cite: 39], to centralize and manage all dependencies. This provides a single source of truth for versions and offers type-safe accessors in build scripts.

* **`[versions]` section:** Declares version numbers as variables (e.g., `composeBom = "2025.07.00"`). This allows multiple libraries to share a version and simplifies updates.
* **`[libraries]` section:** Declares the dependencies themselves, giving them a memorable alias (e.g., `firebase-auth-ktx`) and linking them to their group, name, and version variable.
* **`[plugins]` section:** Declares Gradle plugins, similar to libraries.
* **Usage in `build.gradle.kts`:**
    * Dependencies are added using the type-safe accessors generated by Gradle: `implementation(platform(libs.compose.bom))`.
    * The Bill of Materials (BoM) for Compose (`compose-bom`) and Firebase (`firebase-bom`) are used to manage the versions of related libraries, ensuring they are compatible. The `platform()` keyword enforces this.

* **Key Dependency Groups:**
    * **Core Android & Jetpack:** `core-ktx`, `lifecycle-runtime-ktx`, `activity-compose`. These are essential for a modern Android app with Kotlin and Compose.
    * **Jetpack Compose:** The `compose-bom` and individual artifacts for UI, Graphics, Tooling, and Material 3 design.
    * **Firebase:** The `firebase-bom` and specific dependencies for Authentication (`firebase-auth-ktx`) and Firestore (`firebase-firestore-ktx`).
    * **Testing:** Dependencies for unit tests (`junit`) and instrumented/UI tests (`androidx.test.ext:junit`, `espresso-core`, `compose-ui-test-junit4`).

---
