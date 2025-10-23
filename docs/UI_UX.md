# All24 UI/UX Guide

This document outlines the user interface (UI) and user experience (UX) principles for the All24 application, based on the core concepts of deceptive minimalism and a narrative-driven design.

## The Core Experience: Beyond the Five-Star Rating

The architecture of the All24 app is a direct translation of its manifesto into a tangible user experience. It deliberately eschews the conventions of traditional review platforms to create a product that is focused on discovery, delight, and a deep, narrative-driven exploration of the city's culinary scene.

## The Visual and Kinetic Language: Deceptive Minimalism in Motion

The visual and interactive design of All24 is the physical embodiment of its brand soul. It is sleek, modern, and deceptively minimalistic, revealing layers of depth and personality through interaction. The technical foundation is Jetpack Compose's Material 3 (M3) design system.

### Foundations: Color, Typography, and Shape

*   **Color Palette:** The scheme avoids clichés and draws from the authentic textures of New Orleans.
    *   **Gaslamp Black:** A deep, near-black for dark mode backgrounds.
    *   **Creole Cream:** A warm, soft off-white for light mode.
    *   **Accent Colors:** Deep green, muted terracotta, and vibrant fuchsia, drawn from the city's visual language.
*   **Typography:** A dual type scale is used to be both authoritative and clean.
    *   **Emphasized Scale:** A bold, slightly condensed serif (e.g., Roboto Slab) for high-impact text like list titles and venue names.
    *   **Baseline Scale:** A clean, legible sans-serif (e.g., Roboto) for all body copy and UI labels.
*   **Shape:** Subtly rounded corners for primary containers (cards) create an approachable feel. Key interactive elements, like the "Vouch" button, use more distinct shapes to draw attention and add brand personality.

### The Animation Philosophy: "Everything is Animated, Everything Has a Purpose"

Every interaction is animated using a physics-based motion engine (M3 Expressive) for a responsive, tactile feel.

*   **Systematic Motion:**
    *   **Expressive Scheme:** Used for "hero moments" like major navigational transitions and list item expansions, featuring noticeable bounce.
    *   **Standard Scheme:** Used for utilitarian interactions like opening settings, featuring a more subdued motion.
*   **Key Animation Implementations:**
    *   **Spatial Transitions:** A shared element transition fluidly expands a list card into its full detail view.
    *   **Micro-interactions:** Tapping the "Vouch" button triggers a satisfying "bump" and particle burst.
    *   **Shape Morphing:** A loading indicator might morph into a checkmark upon completion.

## A Granular Deep Dive: Component and Feature Design

### Part 1: The Onboarding Experience – A Cinematic Introduction

*   **Splash Screen & Initial Animation:**
    *   The app will open on a solid "Gaslamp Black" screen.
    *   A single, thin, neon line in the fuchsia accent color will animate, drawing the shape of the All24 logo.
    *   The logo will pulse once with a soft glow, then fluidly morph into a circular progress indicator.
*   **Welcome Carousel:** A three-panel, horizontally-scrolling carousel will introduce the app's core concepts.
    *   **Panel 1: "Welcome to the Real NOLA."** Explains the "Top 24" philosophy.
    *   **Panel 2: "Your Vibe is the New Five Stars."** Introduces the "Vibe Check" and "Vouch" system.
    *   **Panel 3: "Meet Your Guides."** Introduces the roles of Curators and Creators.
*   **Account Creation & Sign-In:**
    *   The final panel will feature "Sign in with Google" and "Continue with Email" buttons.
    *   Tapping "Continue with Email" will animate the appearance of email and password `OutlinedTextField` composables.

### Part 2: The "Lists" Feed – The Heart of Discovery

*   **Anatomy of the Feed:**
    *   A Jetpack Compose `LazyColumn` with a subtle, textured background that has a parallax effect on scroll.
    *   A custom pull-to-refresh animation where the All24 logo morphs from a circle into its full shape and back.
*   **The List "Cover" Card (Collapsed State):**
    *   A full-bleed image loaded with Coil, with a shimmer placeholder effect.
    *   A dark, vertical gradient (scrim) on the bottom of the image to ensure text legibility.
    *   The venue's name in `emphasized headlineSmall` style and the summary in `baseline bodyMedium` style.
    *   A "cut corner" rectangle shape for the rank indicator in the top-left corner.
    *   A bounded M3 ripple effect and a lift-and-shadow effect on press.

### Part 3: The Detail View – The Expanded "Article"

*   **The Shared Element Transition:**
    *   A "hero" animation using `SharedTransitionLayout` that animates the cover image, venue title, and rank indicator from the list to the detail view.
    *   The animation will use a custom `PathMotion` and a physics-based spring with low stiffness and damping.
*   **Collapsing Toolbar Behavior:**
    *   The header image will parallax scroll and shrink as the user scrolls, with the venue's name transitioning into a standard `TopAppBar`.
*   **"Creator Takes" Carousel:**
    *   A horizontal `LazyRow` of `AspectRatio` cards.
    *   Creator avatars will be cropped into an organic, slightly irregular circle.
    *   Tapping a video thumbnail will open a full-screen modal dialog with a Media3 video player.
*   **"The Vitals" Component:**
    *   A `Column` of `Row`s, each with an `Icon` and `Text`.
    *   A custom animated vector drawable for the 24-hour indicator, with a subtle, slow-pulsing neon sign effect.
*   **The "Vibe Check" Interaction Bar:**
    *   An M3 `BottomAppBar` that is always visible.
    *   A "Vouch" button implemented as an M3 Expressive `Split` button, with the main button showing the vouch count and the dropdown revealing "Share" and "Add to Collection" actions.
    *   A "heartbeat" animation on the vouch icon when tapped, a "slot machine" animation for the number change, and a confetti-like particle effect.

### Part 4: The Profile Screen & Community Features

*   **Layout & Motion:**
    *   A `CollapsingToolbarScaffold` with a header containing the user's avatar, display name, and "Tastemaker" badge.
    *   The header will collapse into a simple `TopAppBar` on scroll.
*   **"Vouches" Grid:**
    *   A `LazyVerticalStaggeredGrid` for a masonry-style layout of vouched places.
*   **"Collections" Feature:**
    *   Users can create new collections from their profile or a venue's detail page.
    *   Collections will be displayed as cards on the profile screen and can be shared with friends.
*   **Gamification & Badges:**
    *   A dedicated "Badges" section on the profile will showcase achievements like "Night Owl," "Specialist," and "Curator's Pick."
