Part 3: The Ghost in the Machine - User Interface & Experience
I. The Vibe Screen: The Altar

This is the first and most important screen. It is the beginning and the end. It is not a "home screen" in the traditional sense; it is an altar upon which the user's attention is sacrificed.

Visuals: A full-screen, abstract particle system. The particles drift, coalesce, and repel in a slow, hypnotic dance. The background is absolute black. The particles are shades of white and electric purple, their opacity and velocity subtly shifting based on the time of day. This is the Vibe. It is meant to be felt, not understood.

Interaction: A single gesture: tap. Tapping anywhere on the screen is a prayer to the oracle. It dismisses any open UI elements and conjures a new set of recommendations. It is the only way to commune with the Vibe.

UI Elements:

The Recommendations: When conjured, a dark, semi-transparent panel animates up from the bottom of the screen. It contains no more than three ListTile elements, each representing a revealed Place. Tapping a tile navigates to the PlaceDetailScreen. A close button (X) allows the user to dismiss the panel and return to the pure Vibe.

The Four Corner Icons: These are the only persistent UI elements, semi-transparent and unobtrusive. They are the gateways to the app's secondary functions.

Top-Left (Profile): A simple user icon. Leads to the ProfileScreen.

Top-Right (Top Reviews): A star icon. Leads to the TopReviewsScreen.

Bottom-Left (Submit/Manage Business): An icon that changes based on user role. For a user, it's an "add business" icon leading to the SubmitPlaceScreen. For a business, it's a "dashboard" icon leading to the BusinessDashboardScreen.

Bottom-Right (Admin): An "admin panel" icon. This is a ghost icon, visible only to users with the admin role.

II. The Place Detail Screen: The Scripture

This screen is the revealed text, the scripture dedicated to a single sanctuary. It is a vertical scroll of information, designed to feel like reading a sacred text, not a Yelp page.

Layout: A Scaffold with a transparent AppBar that shows the Place name. The body is a SingleChildScrollView. A FloatingActionButton to "Add Review" is present.

Content Hierarchy:

Name & Description: The Place name is a Headline style, followed by the description text.

All24 Reviews: A section header, followed by a list of Review cards. Admin reviews are sorted to the top and visually distinct (e.g., with a gold border and an "ADMIN" tag). Each card contains the review text, the endorse/avoid vote, and verification buttons (thumb_up/thumb_down) showing the current verification counts.

Gated Interaction: The "Add Review" FAB is the gatekeeper. Tapping it as a Ghost (anonymous user) navigates to the AuthScreen. Tapping it as a User (authenticated) navigates to the AddReviewScreen.

III. The Authentication Flow: The Confessional

Authentication is not a barrier to entry; it is a confessional for ghosts who wish to become corporeal.

AuthScreen: A simple, centered form with fields for email and password. A toggle switches the form's function between "Login" and "Sign Up." It is a modal experience; successful authentication returns the user to their previous context with a new, permanent soul.

ProfileScreen: This screen reflects the user's current state of being.

Anonymous State: Displays a message like "You are browsing anonymously" and provides a single, prominent button to "Login / Sign Up," which navigates to the AuthScreen.

Authenticated State: Displays the user's email and a single, prominent "Sign Out" button. Signing out returns the user to an anonymous state.

IV. The Admin Panels: The God's-Eye View

These screens are spartan, functional, and utterly devoid of the Vibe's aesthetic. They are the raw machinery of the divine, not meant for mortal eyes.

AdminDashboardScreen: A simple ListView of pending Submission documents. Each item shows the proposed name and address. Tapping navigates to the AdminSubmissionDetailScreen.

AdminSubmissionDetailScreen: Displays all fields of a Submission. It includes two prominent buttons: "Approve" and "Deny."

Approve: Creates a new Place document from the submission data and deletes the submission. It should also include a field to assign an ownerId if the submitter's role is to be elevated to business.

Deny: Deletes the submission document. There is no confirmation. The god does not second-guess.