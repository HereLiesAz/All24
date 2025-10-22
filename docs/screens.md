# All24 Application Screens

This document provides an overview of the main screens in the All24 application, as defined in the conceptual blueprint.

## The Home Feed (Lists Screen)

The Home Feed is the primary screen of the application and the user's main entry point for discovery.

*   **Layout:** A single, uncluttered vertical column of "list item covers."
*   **Content:** Each cover represents an establishment on a "Top 24" list and features a full-bleed photograph, the venue's name, its rank, and a witty summary line.
*   **Interaction:** The only interactions are vertical scrolling to browse the feed and tapping a card to navigate to the Detail View. All complexity is intentionally hidden within the cards themselves.

## The Detail View

The Detail View is a full-screen "article" that provides in-depth information about a specific establishment. It is revealed through a fluid shared element transition when a user taps a card on the Home Feed.

*   **Layout:** A rich, multi-faceted layout that presents a complete portrait of the venue.
*   **Content Modules:**
    *   **The All24 Take:** The official 150-word review from the in-house curators.
    *   **Creator Takes:** A horizontal carousel of short-form videos and photo galleries from verified local influencers.
    *   **The Vitals:** Essential information, including address (with a link to maps), hours, phone number, and a link to the menu.
    *   **The People's Voice:** A curated selection of 3-4 of the most insightful user comments.
    *   **"Know Before You Geaux":** A dedicated section for practical, insider tips.

## The Map Screen

The Map Screen is a secondary discovery tool for users who have a specific location in mind.

*   **Layout:** A traditional map-based interface.
*   **Content:** The map will display all All24-vetted spots in the user's vicinity.
*   **Interaction:** Users can pan and zoom the map to explore different neighborhoods and tap on a venue to see a preview of its details.

## The Profile Screen

The Profile Screen is the user's personal space within the app, designed to be a culinary scrapbook of their New Orleans journey.

*   **Layout:** A visually engaging and personalized layout.
*   **Content Modules:**
    *   **"Vouched" Places:** A grid of all the places the user has endorsed.
    *   **"Tastemaker" Comments:** A collection of the user's comments that have been featured by the curators.
    *   **Bookmarked Lists:** Any lists the user has saved for future reference.

## Navigation

A minimal bottom navigation bar provides access to the app's core sections with three clear icons:

*   **Lists:** The default home screen.
*   **Map:** The map-based discovery tool.
*   **Profile:** The user's personal space.
