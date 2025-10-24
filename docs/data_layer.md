# Data Layer

This document outlines the technical stack and data models for the All24 application.

## Technical Stack

The All24 application is built on a modern Android development stack, leveraging the following technologies:

*   **Kotlin:** The primary programming language for the Android application.
*   **Gradle KTS:** The build system for the Android application.
*   **Jetpack Compose:** The UI toolkit for building the native Android application.
*   **Material 3:** The design system for the Android application.
*   **Firebase:** The backend service for authentication and user management.
*   **Spring Boot:** The backend framework for the server-side application.
*   **PostgreSQL:** The database for the server-side application.
*   **Azure/AWS:** The cloud hosting provider for the server-side application.
*   **Google Maps:** The third-party API for location services.

## Data Models

The core data models for the All24 application are as follows:

*   **User:** Represents a user of the application, including their profile information, "Vouched" places, and "Tastemaker" comments.
*   **List:** Represents a "Top 24" list, including its title, description, and a collection of list items.
*   **ListItem:** Represents an entry on a "Top 24" list, including the venue, its rank, and a summary.
*   **Venue:** Represents a physical establishment, including its name, address, hours, and other vital information.
*   **Review:** Represents a user's "Vibe Check," including their "Vouch" and any comments they have submitted.
