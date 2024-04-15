package com.example.cs4520_twitter.ui_tests

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.composables.LoginScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Testing the login UI screen. Per the assignment, we need to
// "Implement at least 1 UI test per screen to ensure UI functionality and responsiveness."
@RunWith(AndroidJUnit4::class)
class LoginUITest {

    // Interface that allows us to test composables
    @get:Rule val composeTestRule = createComposeRule()

    // Start the app, include the log in screen composable
    @Before
    fun setUp() {
        composeTestRule.setContent {
            MaterialTheme {
                LoginScreen()
            }
        }
    }

    @Test
    fun testScreenComponentsCorrectlyInitialized() { // passes
        composeTestRule.onNodeWithText("Babble").assertExists()                // title of login
        composeTestRule.onNodeWithText("Please enter username").assertExists() // user field
        composeTestRule.onNodeWithText("Please enter password").assertExists() // pass field
        composeTestRule.onNodeWithText("Login").assertExists()                 // login button
        composeTestRule.onNodeWithText("Don't have an account?\n" +            // Register text
                "Click to sign up!").assertExists()

        composeTestRule.onNodeWithText("Login").assertExists().assertHeightIsEqualTo(35.dp)
        composeTestRule.onNodeWithText("Login").assertExists().assertWidthIsEqualTo(110.dp)
        }

    // TODO: Create a test for when login button is pressed for invalid user/pass?
    //  Then I think that fulfills requirements
    @Test
    fun testInvalidUserAndPassEntered() { // passes
        // type something invalid in both fields
        // press button
        // check for [?]
    }
}