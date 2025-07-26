### v1.0.0 - 2025-07-26

**Initial Publication: The Forging of the Canon**

- **Part 1: A Manifesto Against the Tyranny of Choice**
    - **Core Philosophy Established:** Deconstructed the flawed premise of contemporary recommendation apps ("The Lie of Rational Choice").
    - **Foundational Concepts Introduced:**
        - **The Annihilation of Convention:** Formalized the rejection of the map, the list, and the search bar as primary interfaces.
        - **The Oracle/Vibe Thesis:** Defined the app's core interaction as a "digital divining rod," with the abstract "Vibe" as the central UI.
      - **The User Hierarchy:*# Changelog

### v1.0.0 - 2025-07-26

* [cite_start]**Initial Publication: The Forging of the Canon** [cite: 209]
    * **Part 1: A Manifesto Against the Tyranny of Choice**
        * [cite_start]**Core Philosophy Established:** Deconstructed the flawed premise of
          contemporary recommendation apps ("The Lie of Rational Choice")[cite: 209].
        * **Foundational Concepts Introduced:**
            * [cite_start]**The Annihilation of Convention:** Formalized the rejection of the map,
              the list, and the search bar as primary interfaces[cite: 209].
            * [cite_start]**The Oracle/Vibe Thesis:** Defined the app's core interaction as a "
              digital divining rod," with the abstract "Vibe" as the central UI[cite: 210].
            * [cite_start]**The User Hierarchy:** Established the four castes of the app's society:
              the spectral `Ghost` (anonymous), the corporeal `User` (authenticated), the ordained
              `Business` (owner), and the divine `Admin` (curator)[cite: 211].
    * **Part 2: The Digital Seance - Backend & Data Models**
        * [cite_start]**Theological Backend Choice:** Solidified Firebase as the "Silent God,"
          chosen for its invisible, serverless architecture[cite: 212].
        * [cite_start]**The Four Rivers (Collections):** Mapped the app's data flow to four
          canonical Firestore collections: `places`, `reviews`, `users`, and
          `place_submissions`[cite: 213, 214].
        * [cite_start]**The Four Horsemen (Data Models):** Provided detailed schematics for the core
          data entities, establishing the fundamental laws of the app's
          universe[cite: 215]. [cite_start]Key fields defined include `Place.ownerId`,
          `Review.isAdminReview`, `UserModel.role`, and `Submission.status`[cite: 216].
    * **Part 3: The Ghost in the Machine - User Interface & Experience**
        * [cite_start]**Screen Blueprints:** Outlined the philosophical and functional design for
          all primary screens[cite: 217].
        * [cite_start]`VibeScreen`: The "Altar," defined by its particle system and "Four Corner
          Gateways" to secondary functions[cite: 217].
        * [cite_start]`PlaceDetailScreen`: The "Sacred Scripture," a vertically scrolling text with
          visually distinct Admin reviews and a gated FAB[cite: 218].
        * [cite_start]`AuthScreen` & `ProfileScreen`: The "Confessional," a system for ghosts to
          become corporeal and to manage their state of being[cite: 219].
        * [cite_start]Admin Panels: The "God's-Eye View," defined by its spartan, utilitarian
          functionality, separate from the main app's aesthetic[cite: 220].
    * **Part 4: The Divine Machinery - Services & Logic**
        * [cite_start]**Architectural Dogma:** Canonized the app's core architectural
          patterns[cite: 221].
        * [cite_start]`FirebaseService` (The One True Conduit): Mandated that all Firestore
          communication must pass through this single, monolithic service[cite: 222].
        * [cite_start]ViewModels (The Minor Prophets): Defined the role of ViewModels as
          interpreters of the divine will, managing state and business logic for individual screens
          via `StateFlow`[cite: 223].
        * [cite_start]`AppNavigation` (The Fates): Established the use of a `sealed class Screen`
          system for typesafe, pre-ordained navigation paths and introduced the `onProtectedAction`
          helper as the gatekeeper for restricted routes[cite: 224].
    * **Part 5: The Sacred Rituals - Core User Flows**
        * [cite_start]**Narrative Logic Defined:** Provided detailed, step-by-step narrative
          descriptions for the app's most critical user-system interactions, translating abstract
          goals into a concrete sequence of events[cite: 225].
        * [cite_start]**Ritual of Revelation:** User taps Vibe -> ViewModel shuffles places -> UI
          animates results[cite: 226].
        * [cite_start]**Ritual of Judgment:** User taps verify -> ViewModel calls service ->
          Firestore transaction updates review -> UI recomposes via `Flow`[cite: 227].
        * [cite_start]**Ritual of Creation:** User submits form -> ViewModel calls service -> A new
          prayer is created in the `place_submissions` collection[cite: 228].
        * [cite_start]**Ritual of Ordination:** Admin taps approve -> ViewModel calls service -> A
          `WriteBatch` transmutes a `Submission` into a `Place`[cite: 229].* Established the four
          castes of the app's society: the spectral `Ghost` (anonymous), the corporeal `User` (
          authenticated), the ordained `Business` (owner), and the divine `Admin` (curator).

- **Part 2: The Digital Seance - Backend & Data Models**
    - **Theological Backend Choice:** Solidified Firebase as the "Silent God," chosen for its invisible, serverless architecture.
    - **The Four Rivers (Collections):** Mapped the app's data flow to four canonical Firestore collections:
        - `places`: The sanctuaries.
        - `reviews`: The prophecies.
        - `users`: The souls.
        - `place_submissions`: The purgatorial prayers.
    - **The Four Horsemen (Data Models):** Provided detailed schematics for the core data entities, establishing the fundamental laws of the app's universe. Key fields defined include `Place.ownerId`, `Review.isAdminReview`, `UserModel.role`, and `Submission.status`.

- **Part 3: The Ghost in the Machine - User Interface & Experience**
    - **Screen Blueprints:** Outlined the philosophical and functional design for all primary screens.
        - **`VibeScreen`:** The "Altar," defined by its particle system and "Four Corner Gateways" to secondary functions.
        - **`PlaceDetailScreen`:** The "Sacred Scripture," a vertically scrolling text with visually distinct Admin reviews and a gated FAB.
        - **`AuthScreen` & `ProfileScreen`:** The "Confessional," a system for ghosts to become corporeal and to manage their state of being.
        - **Admin Panels:** The "God's-Eye View," defined by its spartan, utilitarian functionality, separate from the main app's aesthetic.

- **Part 4: The Divine Machinery - Services & Logic**
    - **Architectural Dogma:** Canonized the app's core architectural patterns.
        - **`FirebaseService` (The One True Conduit):** Mandated that all Firestore communication must pass through this single, monolithic service.
        - **ViewModels (The Minor Prophets):** Defined the role of ViewModels as interpreters of the divine will, managing state and business logic for individual screens via `StateFlow`.
        - **`AppNavigation` (The Fates):** Established the use of a `sealed class Screen` system for typesafe, pre-ordained navigation paths and introduced the `onProtectedAction` helper as the gatekeeper for restricted routes.

- **Part 5: The Sacred Rituals - Core User Flows**
    - **Narrative Logic Defined:** Provided detailed, step-by-step narrative descriptions for the app's most critical user-system interactions, translating abstract goals into a concrete sequence of events.
        - **Ritual of Revelation:** User taps Vibe -> ViewModel shuffles places -> UI animates results.
        - **Ritual of Judgment:** User taps verify -> ViewModel calls service -> Firestore transaction updates review -> UI recomposes via `Flow`.
        - **Ritual of Creation:** User submits form -> ViewModel calls service -> A new prayer is created in the `place_submissions` collection.
        - **Ritual of Ordination:** Admin taps approve -> ViewModel calls service -> A `WriteBatch` transmutes a `Submission` into a `Place`.