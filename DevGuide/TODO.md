# Development Roadmap

This document outlines pending development tasks for the new carousel-based architecture.

* **Task: Complete `PlaceDetailScreen` Implementation**
    * **Objective:** To create a fully functional and visually rich detail screen.
    * **Requirements:**
        1. Create a dedicated `PlaceDetailViewModel` that fetches all necessary data for a given
           `placeId`.
        2. The screen must elegantly display all `Place` information (name, description, etc.).
        3. The shared element transition for the place name must be polished.
        4. Display associated `Review` documents for the place.
        5. Implement a "Write a Review" FAB that is gated by authentication.

* **Task: Refine Carousel Item Interaction**
    * **Objective:** To make the carousel items more interactive and informative.
    * **Requirements:**
        1. Finalize the design for overlays on carousel items in their different states (large,
           medium, small).
        2. Implement `onClick` and `onLongClick` handlers for the items in the `CarouselAdapter`.

* **Task: Replace Mock Data Source**
    * **Objective:** To connect the application to a live backend.
    * **Requirements:**
        1. Replace the mock data in `SheetsService` with live calls to Google Sheets or a Firestore
           database.
        2. Ensure the `MainCarouselViewModel` correctly handles loading, error, and empty states
           from the live data source.