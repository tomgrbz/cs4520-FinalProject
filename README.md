
Some comments for reading ProfileScreen.kt
-----------------------------------------

- MainActivity currently has a ProfileScreen composable, so the app should display how it looks so far
- The ProfileScreen composable is kind of long since it contains all of the screen components. It includes lines 73 to 278.
- Added the Bab card which we can reuse for the feed starts at line 283 and ends at 352. All code after that is stuff that would be nice to implement for better organization.
- I organized ProfileScreen as such: First, there is a Box composable which contains the custom gradient theme. Within that, I used a ConstraintLayout composable which contains all of the screen's components. Each component has a reference (line 91).
- Some things that are still needed: The bab card needs that heart/like symbol. Need to add edit screen and my followers screen.
- Also, need a version of the screen for representing other users (not the logged in user, as this current UI has it)
- I think the Date type is deprecated (?). So, should we just use a string for the date field of a bab post?
- Glide also isn't displaying the given URL and I'm not sure if it's due to the URL or something else. I am also using Glide's compose library which is still in beta form so it makes you add a line like "@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)" for composables using it. I'm using this library since Glide's base library I believe needs an instance of an ImageView to load into which we don't use in composables.
- We also need the global logged in user. Finally, get this screen to fetch data from the database instead of using dummy data, but we have the data types now thanks to Ally!
