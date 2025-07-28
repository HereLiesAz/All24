# Changelog

### v2.0.0 - 2025-07-27

**The Gyroscopic Reformation**

This is a complete philosophical and architectural overhaul of the application, moving from a
minimalist, deconstructionist model to a fluid, expressive, and more conventional Browse experience.

- **UI/UX Overhaul:**
    - **DEPRECATED**: The abstract `VibeScreen` and its particle system have been completely
      removed. The core philosophy of "annihilating convention" has been abandoned.
    - **NEW**: The primary interface is now the `MainCarouselScreen`, a dual-axis, multi-browse
      carousel grid. Users navigate vertically between curated categories and horizontally through
      items within each category.
- **Architectural Shift:**
    - The project has shifted from a pure Jetpack Compose architecture to a **hybrid model** that
      utilizes both Compose and the traditional Android View system.
    - This was done to incorporate the official
      `com.google.android.material.carousel.CarouselLayoutManager` for `RecyclerView`, which
      provides the exact M3 Expressive carousel behavior.
    - Jetpack Compose's `AndroidView` is now used to host the `RecyclerView`-based carousels within
      the larger Compose UI.
- **Animation System:**
    - **NEW**: Implemented M3 Expressive "Choreographed Transitions" using Jetpack Compose's
      `SharedTransitionLayout`. Tapping a carousel item now initiates a seamless transition to the
      detail screen.
    - **NEW**: Added M3 Expressive "Responsive Micro-interactions" with tap-and-scale animations on
      carousel items.
- **Data Model:**
    - The `Place` data model has been updated with a `tags: List<String>` field to facilitate the
      new category-based Browse system.

### v1.0.0 - 2025-07-26

**Initial Publication: The Forging of the Canon**

- **Part 1: A Manifesto Against the Tyranny of Choice**
    - **Core Philosophy Established:** Deconstructed the flawed premise of contemporary
      recommendation apps ("The Lie of Rational Choice").
- **Part 2: The Digital Seance - Backend & Data Models**
    - **Theological Backend Choice:** Solidified Firebase as the "Silent God."
- **Part 3: The Ghost in the Machine - User Interface & Experience**
    - **Screen Blueprints:** Outlined the philosophical and functional design for all primary
      screens, centered on the `VibeScreen`.
- **Part 4: The Divine Machinery - Services & Logic**
    - **Architectural Dogma:** Canonized the app's core architectural patterns based on pure Jetpack
      Compose and ViewModels.
- **Part 5: The Sacred Rituals - Core User Flows**
    - **Narrative Logic Defined:** Provided detailed narrative descriptions for app interactions.