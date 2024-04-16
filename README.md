
Some comments
-----------------------------------------

- Can I add a method to the User API to retrieve a user?
- To use the app: please first log in with valid credentials to access the profile screen.
- I think there may be an issue with the "testuser" user's userID (it's 123). I get an error at line "UUID.fromString(LoggedInUser.loggedInUserId)" saying that testuser has an invalid UUID string after logging in and navigating to the profile.
- But I can log in with users like 'ben10' and '#1pokemonfan' and I can navigate to profile which loads correctly (aside from the banner username, but I need an api method to retrieve a user).
- I also think there may be an issue with the image URL's from the API's users, it gives me res/bear when I obtain it
- I also added registration functionality. If you click on the text at the bottom, it will change to login button
- to a register one, and a user can successfully register if both text fields are not blank. I used the signup api for this
- I tested it myself with username and password "foo". Then if you navigate to the profile screen, there is a blank profile.

![Alt text](example_login.png)

![Alt text](uuid_error_for_testuser.png)

![Alt text](registration.png)

![Alt text](new_registered_user.png)