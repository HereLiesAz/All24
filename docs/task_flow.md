# All24 User Task Flows

This document outlines the primary user task flows within the All24 application, detailing how users will interact with the app's core features.

## 1. The Discovery Flow: Finding a New Experience

This is the primary user journey, focused on proactive and serendipitous discovery.

1.  **Launch App:** The user opens the All24 app.
2.  **Browse Home Feed:** The user is immediately presented with the Home Feed, a visually rich, endlessly scrollable feed of "Top 24" lists.
3.  **Scroll and Explore:** The user scrolls vertically through the lists, browsing the "covers" of different establishments. Each cover provides a compelling image and a witty summary to entice the user.
4.  **Select a Venue:** The user finds a venue that piques their interest and taps on its card.
5.  **View Detail Screen:** A fluid, shared element transition expands the card into the full-screen Detail View.
6.  **Explore Venue Details:** The user can now explore the rich content on the Detail View:
    *   Read "The All24 Take."
    *   Swipe through the "Creator Takes" carousel.
    *   Check "The Vitals" for the address, hours, and menu.
    *   Read the curated "People's Voice" comments.
    *   Check the "Know Before You Geaux" tips.
7.  **Take Action:** From the Detail View, the user can choose to get directions, call the venue, or bookmark the list for later.

**Implementation Notes:**
* The navigation from the Home Screen to the Detail Screen is implemented in `app/src/main/java/com/hereliesaz/all24/ui/navigation/AppNavigation.kt`.
* The `HomeScreen` passes the `itemId` of the selected venue to the `DetailScreen` as a navigation argument.
* A shared element transition is implemented for the navigation between the two screens.

## 2. The "Vibe Check" Flow: Leaving Feedback

This flow describes how a user contributes to the community by leaving their own feedback.

1.  **Navigate to a Venue:** The user first navigates to the Detail View of a venue they have visited.
2.  **Endorse with a "Vouch":** If the user loves the place, they can tap the "Vouch" button. The button provides a satisfying animated response, and the total vouch count for the venue is updated.
3.  **Leave a Comment:** To leave more detailed feedback, the user initiates the comment process.
4.  **Engage with Prompts:** Instead of an empty text box, the user is presented with creative, Mad Libs-style prompts (e.g., "This place is perfect for \_\_\_\_\_\_.").
5.  **Submit Comment:** The user fills in the prompts and submits their comment.
6.  **Curator Review:** The submitted comment is sent to the All24 Curators for review. If it is selected for its wit, insight, or helpfulness, it will be featured in "The People's Voice" section, and the user will be awarded a "Tastemaker" badge on their profile.

## 3. The Profile Engagement Flow: Reviewing Personal Activity

This flow describes how a user interacts with their personal space within the app.

1.  **Navigate to Profile:** The user taps the "Profile" icon in the bottom navigation bar.
2.  **View Profile Screen:** The user is taken to their Profile Screen.
3.  **Explore Personal Collections:** The user can browse:
    *   A grid of all the places they have "Vouched" for.
    *   A collection of their "Tastemaker" comments that have been featured.
    *   A list of all the lists they have bookmarked.
4.  **Revisit a Venue:** The user can tap on any of their "Vouched" places to quickly return to its Detail View.
