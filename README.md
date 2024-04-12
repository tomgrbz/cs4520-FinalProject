
Some comments for reading ProfileScreen.kt
-----------------------------------------

- MainActivity currently has a ProfileScreen composable, so the app should display how it looks so far
- The ProfileScreen composable is kind of long since it contains all of the screen components. It includes lines 81 to 286.
- Added the Bab card which we can reuse for the feed starts at line 291 and ends at 379. Code after that is just stuff that would be nice to implement for better organization.
- I organized ProfileScreen as such: First, there is a Box composable which contains the custom gradient theme. Within that, I used a ConstraintLayout composable which contains all of the screen's components. Each component has a reference (line 98).
- Some things that are still needed: Edit Screen. A way to change the profile when viewing other users (not the logged in user). We need the global logged in user.
- I'm using the Glide Compose library since Glide's base library I believe needs an instance of an ImageView to load into which we don't use in composables.

![Alt text](profile_screen_photo.png)