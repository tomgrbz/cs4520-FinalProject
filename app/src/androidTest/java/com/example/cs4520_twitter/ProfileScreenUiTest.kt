package com.example.cs4520_twitter

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.composables.UserProfileScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class ProfileScreenUiTest {
        @get: Rule
        val composeTestRule = createComposeRule()
        @Before
        fun setUp() {
            composeTestRule.setContent {
                MaterialTheme {
                    val navController = rememberNavController()
                    LoggedInUser.loggedInUserId = "eb1167b3-67a9-4378-bc65-c1e582e2e662"
                    UserProfileScreen(navController = rememberNavController())

                }
            }
        }
        @Test
        fun testProfileSetup(){
            // basic components should be on screen
            composeTestRule.onNodeWithText("Followers:", substring = true).assertExists()
            composeTestRule.onNodeWithText("Following:", substring = true).assertExists()
            composeTestRule.onNodeWithText ("Babs:", substring = true)
            composeTestRule.onNodeWithText("Edit").assertExists()
            composeTestRule.onNodeWithText("My Followers").assertExists()
        }
}