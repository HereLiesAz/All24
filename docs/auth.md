# All24 Authentication and User Profiles

This document outlines the authentication mechanism and the features related to user profiles within the All24 application.

## Authentication

### Technology Stack

User authentication is handled using **Firebase Authentication**. This provides a secure, easy-to-use, and scalable solution for managing user accounts.

### User Account Creation

The `AuthScreen` provides the following options for user authentication:

*   Email and password sign-in (UI only, logic to be implemented).
*   Email and password account creation (UI only, logic to be implemented).
*   Google Sign-In.

**Implementation Notes:**
* The `AuthScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/auth/AuthScreen.kt`.
* Google Sign-In is integrated with Firebase Authentication using the `AuthViewModel`.

## User Profiles

The user profile is a core component of the All24 experience, designed to be a personal culinary scrapbook of a user's New Orleans journey.

### Profile Screen Features

The `ProfileScreen` displays the following information for the currently signed-in user:

*   Display name and email.
*   A "Sign Out" button.
*   A section for "Vouched" places (with placeholder data).
*   A section for "Tastemaker" comments (with placeholder data).

**Implementation Notes:**
* The `ProfileScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/screens/ProfileScreen.kt`.
* The screen fetches the current user from Firebase Authentication.
