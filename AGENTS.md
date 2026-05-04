# All24 Agent Guide

This document provides guidance for AI agents working on the All24 project.

## Project Overview

All24 is a New Orleans culinary companion app. It features curated "Top 24" lists, a "Vibe Check" review system, and is built with a focus on authenticity and a 24-hour city culture.

## Architecture

The project is a multi-module Gradle project:

-   `app/`: The Android application (Kotlin, Jetpack Compose, Material 3).
-   `backend/`:
    -   A Node.js project acting as a Google Sheets proxy.
    -   A Kotlin Spring Boot application.

## Development Conventions

### Jetpack Compose
- Use standard `for` loops over `forEach` in `@Composable` functions to reduce overhead.
- Prioritize `LazyColumn` for lists.
- Follow "Deceptive Minimalism": keep UI simple at first glance, reveal depth through animation.
- Use Material 3 Expressive motion (physics-based springs).

### Data Management
- Use `BuildConfig` (enabled in `app/build.gradle.kts`) to access API keys from `local.properties`.
- Use Room for local caching to ensure the app works optimally offline.

### Testing
- Unit tests should be placed in `app/src/test`.
- Instrumented UI tests should be placed in `app/src/androidTest`.
- Run tests using `./gradlew test` or `./gradlew connectedAndroidTest`.

## TODO List

1.  **Project Structure & Cleanup**:
    - [x] Remove misplaced dot-notated files.
    - [x] Update `AGENTS.md` with project-specific instructions.
2.  **Database & Data Persistence**:
    - [ ] Implement Room database for offline caching of venue data.
    - [ ] Ensure smooth synchronization with the Google Sheets backend.
3.  **UI/UX Refinement (Mobile Optimization)**:
    - [ ] Transition to Material 3 Expressive components.
    - [ ] Implement shared element transitions for the "Top 24" list items.
    - [ ] Add physics-based animations (springs) for a more fluid feel.
    - [ ] Implement the "Vibe Check" system (Vouch + Mad Libs prompts).
4.  **Testing Framework**:
    - [ ] Create `app/src/test` and `app/src/androidTest` directories.
    - [ ] Add baseline tests for navigation and data fetching.
5.  **Environment & Deployment**:
    - [ ] Verify `local.properties` integration and `BuildConfig` generation.
    - [ ] Ensure `google-services.json` is correctly handled.
