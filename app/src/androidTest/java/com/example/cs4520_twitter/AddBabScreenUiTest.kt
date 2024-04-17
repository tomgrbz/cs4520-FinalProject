package com.example.cs4520_twitter

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.composables.AddBabScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class AddBabScreenUiTest {
    @get: Rule
    val composeTestRule = createComposeRule()
    @Before
    fun setUp() {
        composeTestRule.setContent {
            MaterialTheme {
                LoggedInUser.loggedInUserId = "eb1167b3-67a9-4378-bc65-c1e582e2e662"
                AddBabScreen()
            }
        }
    }
    @Test
    fun testAddBabSetup(){
        // basic components should be on screen
        composeTestRule.onNodeWithText("@", substring = true).assertExists() // for user card
        composeTestRule.onNodeWithText("What do you wanna bab about?").assertExists()
        composeTestRule.onNodeWithText("Post").assertExists() // for button
    }
}