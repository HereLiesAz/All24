# Project Manifesto

## 1. Core Philosophy & Mission Statement

The foundational principle of the All24 application is the deliberate and systematic deconstruction
of conventional mobile UI paradigms for discovery and recommendation. The project's mission is to
provide an antidote to the "choice paralysis" and cognitive overload induced by modern data-rich
applications. Where contemporary apps maximize user control through comprehensive filters,
searchable lists, and interactive maps, All24 minimizes it. It posits that true serendipity arises
not from infinite choice, but from meaningful constraints.

The application's core purpose is not to function as a utility for finding a specific,
user-predetermined location. Instead, it serves as a curated, narrative-driven experience. It aims
to guide the user toward unexpected discoveries by replacing explicit, user-driven queries with a
system-driven, randomized presentation of a strictly limited set of options. The core interaction is
designed to be evocative and atmospheric, prioritizing mood and a sense of mystique over
informational density.

## 2. Key Anti-Goals

To clarify the project's direction, it is crucial to define what it explicitly aims *not* to be:

* **Not a Database:** The application is not a comprehensive, encyclopedic directory of all
  available locations. The `places` collection is a curated canon, not an exhaustive list.
* **Not a Search Engine:** There will be no text-based search functionality for places, categories,
  or locations. Discovery is not meant to be a direct query-response interaction.
* **Not a Social Network:** While users have identities and can contribute content, the application
  avoids features common to social networks, such as user profiles, direct messaging, or "friending"
  mechanisms. Interactions are mediated through content (places and reviews), not between users
  directly.
* **Not a Map:** The application will not feature a user-facing, interactive map for Browse or
  navigation. Geographic location is a data point for curation, not the primary interface for
  exploration.

## 3. Interface & Design Principles

The application's interface design is governed by a strict set of rules derived from the core
philosophy:

* **Annihilation of Convention:** The three primary tools of modern recommendation apps—the
  interactive map, the searchable/sortable list, and the text-based search bar—are explicitly
  forbidden as primary interfaces for discovery.
* **Primacy of the "Vibe":** The main screen, `VibeScreen`, is the heart of the application. It is
  an abstract, interactive space designed to establish a specific aesthetic and mood. Its sole
  purpose is to serve as the entry point for invoking recommendations, an act referred to as "
  conjuring."
* **Gated Interaction:** Access to features that allow content creation or modification (e.g.,
  adding reviews, submitting places, editing owned places) is strictly gated based on the user's
  authentication state and role. This reinforces the value of contribution and creates a clear
  distinction between passive consumption and active participation.
* **Minimalism & Focus:** UIs are designed to be spartan and focused. Screens present only the
  information necessary for the task at hand, avoiding extraneous details, advertisements, or
  unrelated navigation paths. The Admin Panel UI is the ultimate expression of this, being purely
  functional and devoid of the main app's aesthetic.

## 4. User Role Hierarchy & Philosophy

The application defines a four-tiered user hierarchy. Each role represents a different level of
trust and responsibility within the application's ecosystem, granting a specific set of permissions.
This system is the primary mechanism for content curation and management.

1. **Anonymous User (Ghost):** This is the default, unauthenticated state. The philosophy for this
   role is that of a "spectator." The user can observe the world, view its curated content, and
   experience the core "Vibe," but cannot influence it. They are provided a temporary, stable
   identity via Firebase Anonymous Authentication to allow for basic state management, but they
   leave no permanent mark.
2. **Authenticated User (User):** This is the standard role for a registered user. The philosophy
   here is that of a "contributor." By choosing to register, the user has expressed a desire to
   participate in the ecosystem. They are granted the ability to create content—their voice, in the
   form of `Review` documents, and their suggestions, in the form of `Submission` documents. Their
   contributions, however, are subject to the existing structure and community validation.
3. **Business Owner (Business):** This is an elevated, trusted role assigned by an Administrator.
   The philosophy is that of a "steward" or "keeper." A business user is granted direct control over
   the canonical data for a specific `Place` they have been assigned. This role acknowledges that
   certain users have a legitimate, vested interest in maintaining the accuracy of specific location
   data, while still keeping that data within the app's curated aesthetic.
4. **Administrator (Admin):** The highest-level role, representing the "curator" or "editor" of the
   application's world. The Admin's role is to enforce the project's vision. They have the ultimate
   authority on what content becomes canonical (by approving/denying submissions), how content is
   framed (by writing `isAdminReview` reviews), and who is granted stewardship (by assigning user
   roles).