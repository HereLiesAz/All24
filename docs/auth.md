# All24 Authentication and User Profiles

This document outlines the authentication mechanism and the features related to user profiles within the All24 application.

## Authentication

### Technology Stack

User authentication is handled using **Firebase Authentication**. This provides a secure, easy-to-use, and scalable solution for managing user accounts.

### The Onboarding & Authentication Flow

The authentication flow is integrated into the first-time user onboarding experience.

1.  **Welcome Carousel:** New users are greeted with a three-panel carousel that introduces the app's core concepts.
2.  **Sign-In Options:** The final panel of the carousel presents two primary sign-in options:
    *   "Sign in with Google"
    *   "Continue with Email"
3.  **UI/UX:**
    *   The sign-in buttons are large "squircle" shaped buttons for a friendly, modern look.
    *   Tapping "Continue with Email" animates the appearance of email and password `OutlinedTextField` composables.
    *   Validation feedback for the email and password fields is communicated with a subtle color change and a gentle "shake" animation.

### User Account Creation

The `AuthScreen` provides the following options for user authentication:

*   Email and password sign-in.
*   Email and password account creation.
*   Google Sign-In.

**Implementation Notes:**
*   The `AuthScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/auth/AuthScreen.kt`.
*   Google Sign-In is integrated with Firebase Authentication using the `AuthViewModel`.

## User Profiles

The user profile is a core component of the All24 experience, designed to be a personal culinary scrapbook of a user's New Orleans journey.

### Profile Screen Features

The `ProfileScreen` displays the following information for the currently signed-in user:

*   Display name and email.
*   A "Sign Out" button.
*   A section for "Vouched" places.
*   A section for "Tastemaker" comments.
*   A section for "Collections."
*   A section for "Badges."

**Implementation Notes:**
*   The `ProfileScreen` is implemented in `app/src/main/java/com/hereliesaz/all24/ui/screens/ProfileScreen.kt`.
*   The screen fetches the current user from Firebase Authentication.
