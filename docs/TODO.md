# All24 Production Roadmap and TODO List

This document outlines the step-by-step action plan for transforming the All24 concept into a production-ready mobile application.

## Phase I: Pre-Production & Strategy (Completed)
- [x] All pre-production and strategy tasks are considered conceptually complete.

## Phase II: Design & Prototyping (Completed)
- [x] All design and prototyping tasks are considered conceptually complete.

## Phase III: Development & Implementation

### Sprint 0: Environment & Theming
- [x] **Environment & DevOps Setup:** Basic project structure is in place.
- [x] **Implement M3 Expressive Theme:** Define and apply the app's color, typography, and shape systems.

### Sprint 1: Onboarding & Core Navigation
- [x] **Implement Onboarding Carousel:** Build the three-panel cinematic introduction.
- [x] **Implement Splash Screen Animation:** Created a placeholder for the animation.
- [x] **Implement Core Navigation:** Set up the bottom navigation bar for the Lists, Map, and Profile screens.

### Sprint 2: The "Lists" Feed
- [x] **Build Home Feed UI:** Create the basic `LazyColumn` and placeholder card UI.
- [x] **Implement Parallax Background:** Add the subtle, textured background with a parallax effect on scroll.
- [ ] **Implement Pull-to-Refresh Animation:** Create the custom logo morphing animation.
- [ ] **Refine List "Cover" Card:** Implement the full design, including the scrim, rank indicator, and press effects.

### Sprint 3: The "Detail" View
- [x] **Build Detail Screen UI:** Create the basic layout with placeholder content.
- [x] **Implement Shared Element Transition:** Create the "hero" animation from the list to the detail view.
- [ ] **Implement Collapsing Toolbar Behavior:** Create the parallax and shrink effect for the header image.
- [ ] **Implement "Creator Takes" Carousel:** Build the horizontal `LazyRow` and the video playback modal.
- [ ] **Implement "Vitals" Component:** Add the animated 24-hour indicator.

### Sprint 4: The "Vibe Check" System
- [x] **Implement "Vouch" Button UI:** Add the basic button to the `DetailScreen`.
- [x] **Implement Prompted Comment Submission UI:** Add the UI for the Mad Libs-style prompts.
- [ ] **Implement "Vibe Check" Interaction Bar:** Build the `BottomAppBar` with the `Split` button for "Vouch," "Share," and "Add to Collection" actions.
- [ ] **Implement "Vouch" Animations:** Create the "heartbeat," "slot machine," and confetti animations.
- [ ] **Implement Comment Submission Logic:** Integrate with the backend to save user comments.

### Sprint 5: User Profiles & Authentication
- [x] **Create `AuthScreen` UI:** Build the UI for email/password and Google Sign-In.
- [x] **Integrate Firebase Authentication for Google Sign-In:** Implement the Google Sign-In logic.
- [x] **Create `ProfileScreen` UI:** Build the basic layout with placeholder data.
- [ ] **Implement `CollapsingToolbarScaffold`:** Create the collapsing header effect.
- [ ] **Implement "Vouches" Grid:** Build the `LazyVerticalStaggeredGrid` for the masonry-style layout.
- [ ] **Implement Email/Password Authentication:** Add the logic for email/password sign-in and account creation.

### Sprint 6: Community Features
- [ ] **Implement "Collections" Feature:** Build the UI for creating, viewing, and sharing collections.
- [ ] **Implement "Badges" Feature:** Build the UI for displaying gamification achievements.
- [ ] **Integrate Collections & Badges with Backend:** Add the logic to save and retrieve user collections and badges.

## Phase IV: Content & Community Ignition (Ongoing)
- [ ] **Initial Content Seeding:** Curators to create the initial batch of "Top 24" lists.
- [ ] **Creator Program Recruitment:** Identify and onboard "Founding Creators."
- [ ] **Pre-Launch Marketing Campaign:** Launch a "coming soon" landing page and social media presence.

## Phase V: Testing & Go-to-Market
- [ ] **Alpha & Beta Testing:** Conduct internal and closed beta testing.
- [ ] **Go-to-Market (GTM) Execution:** Prepare the Google Play Store listing and press kit.
- [ ] **Deployment:** Submit the final release candidate to the Google Play Store.
- [ ] **Launch Day:** Publish the app and announce the launch.

## Phase VI: Post-Launch, Monitoring & Iteration (Ongoing)
- [ ] **Performance Monitoring & Maintenance:** Monitor app analytics, server performance, and crash reports.
- [ ] **Community Management & Content Cadence:** Engage with the community and publish new content.
- [ ] **KPI Tracking & Analysis:** Track KPIs to inform future development.
- [ ] **Iterative Development:** Begin the development cycle for v1.1.
