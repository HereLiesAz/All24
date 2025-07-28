# UI/UX Documentation

This document details the design, layout, and interaction logic for the key screens in the All24
application.

## 1. `MainCarouselScreen`

* **Purpose**: The main entry point and primary interaction screen. It allows users to browse
  curated content through a fluid, dual-axis carousel grid.
* **Layout & Theming**:
  * The screen is built around a vertical `VerticalPager` in Jetpack Compose. Each page of the pager
    represents a content category.
  * The pager is configured as a "multi-browse" carousel, with a large, focused central item and
    smaller items for the previous/next categories peeking in from the top and bottom.
  * Each vertical page hosts a horizontal, multi-browse carousel built using `RecyclerView` and
    `CarouselLayoutManager`, which is embedded using the `AndroidView` composable.
* **Interaction**:
  * **Vertical Swipe**: The user swipes up or down to cycle through the main content categories (
    e.g., "Happy Hours", "24-Hour Bars").
  * **Horizontal Swipe**: Within a category, the user swipes left or right to browse the individual
    places or events.
  * **Tap**: Tapping the focused item in the horizontal carousel initiates a shared element
    transition to the `PlaceDetailScreen`.
* **State Handling**:
  * `MainCarouselViewModel` fetches all places and organizes them into a map of
    `Map<String, List<Place>>`.
  * This data is passed to the `VerticalPager`. Each page then uses its subset of the data to
    configure its `CarouselAdapter`.

## 2. `PlaceDetailScreen`

* **Purpose**: To display all relevant information for a single `Place`.
* **Layout**:
  * A simple, vertical `Column` layout in Jetpack Compose.
  * The screen participates in a shared element transition with `MainCarouselScreen`, with the
    `Place` name animating seamlessly between the two screens.
* **Content Hierarchy & Logic**:
  * The screen prominently displays the place's name, which serves as the "hero" element of the
    transition.
  * Below the name, it shows the description, reviews, and other relevant metadata.