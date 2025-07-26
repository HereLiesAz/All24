# Firebase Security Rules

## 1. Introduction & Core Principles

This document defines the security strategy for the Firestore database. **This is the most critical
server-side component of the application's security model.** Client-side checks can always be
bypassed; Firestore Security Rules are enforced on Google's servers and are non-negotiable.

The guiding principle is the **principle of least privilege**: a user should only have the exact
permissions required to perform their legitimate actions, and no more. By default, access to all
data should be denied.

**Default Rule (Unsafe):**
A common starting point is to deny all access by default:
rules_version = '2';
service cloud.firestore {
match /databases/{database}/documents {
// Deny all reads and writes by default.
match /{document=**} {
allow read, write: if false;
}
}
}

We then open up access on a collection-by-collection basis.

## 2. Authentication Context

The `request.auth` object is available in security rules and contains the information of the
authenticated user making the request. `request.auth.uid` is the user's unique ID. If `request.auth`
is `null`, the user is not authenticated.

## 3. Collection-by-Collection Rule Definitions

Below are the detailed security rules required for each collection, designed to enforce the
application's business logic on the backend.

### `users/{userId}`

Users should only be able to read and modify their own data, and they must not be able to elevate
their own privileges.

match /users/{userId} {
// READ: A user can only read their own document.
allow read: if request.auth != null && request.auth.uid == userId;

// CREATE: A user can only create their own document.
// The incoming document's 'uid' must match the user's auth uid.
// The role must be 'user' on creation.
allow create: if request.auth != null && request.auth.uid == userId
&& request.resource.data.uid == request.auth.uid
&& request.resource.data.role == 'user';

// UPDATE: A user can only update their own document.
// They cannot change their role. An admin must do that.
allow update: if request.auth != null && request.auth.uid == userId
&& request.resource.data.role == resource.data.role;

// DELETE: Disallow deleting user documents.
allow delete: if false;
}

### `places/{placeId}`

Places are publicly readable but highly controlled in terms of writes. Only admins can create/delete
them, and only owners or admins can update them.

match /places/{placeId} {
// READ: All users, including anonymous ones, can read place data.
allow read: if true;

// CREATE: Only an authenticated admin can create a new place.
allow create: if isAdmin();

// UPDATE: Only the business owner or an admin can update a place.
allow update: if isOwner(resource.data.ownerId) || isAdmin();

// DELETE: Only an admin can delete a place.
allow delete: if isAdmin();
}

### `reviews/{reviewId}`

Reviews are publicly readable. Creation is restricted to authenticated users. Updates are heavily
restricted.

match /reviews/{reviewId} {
// READ: All users can read reviews.
allow read: if true;

// CREATE: An authenticated user can create a review if...
// 1. The userId on the new review matches their own uid.
// 2. They are not trying to create an admin review.
allow create: if request.auth != null
&& request.resource.data.userId == request.auth.uid
&& request.resource.data.isAdminReview == false;

// UPDATE: Allow if an authenticated user is only changing the
// 'endorsedBy' or 'avoidedBy' fields. All other fields must remain the same.
allow update: if request.auth != null &&
request.resource.data.diff(resource.data).affectedKeys()
.hasOnly(['endorsedBy', 'avoidedBy']);

// DELETE: Only an admin can delete a review.
allow delete: if isAdmin();
}

### `place_submissions/{submissionId}`

Submissions are highly confidential. Only the creator can create one, and only admins can read or
delete them.

match /place_submissions/{submissionId} {
// READ: Only admins can read submissions.
allow read: if isAdmin();

// CREATE: An authenticated user can create a submission if the
// 'submittedBy' field matches their own uid.
allow create: if request.auth != null
&& request.resource.data.submittedBy == request.auth.uid;

// UPDATE: Disallow updates. Submissions are immutable until deleted.
allow update: if false;

// DELETE: Only an admin can delete (approve/deny) a submission.
allow delete: if isAdmin();
}

## 4. Helper Functions & Deployment

To keep rules clean and reusable, helper functions should be used.

// Placed at the top level of the rules file
function isAdmin() {
// Check if the user's document in the 'users' collection has the 'admin' role.
return request.auth != null &&
get(/databases/(database)/documents/users/(request.auth.uid)).data.role == 'admin';
}

function isOwner(ownerId) {
return request.auth != null && request.auth.uid == ownerId;
}

These rules must be deployed to the Firebase project using the Firebase CLI (`firebase deploy --on