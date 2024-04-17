package com.example.cs4520_twitter

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.composables.FollowingScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class FollowingScreenUiTest {
    @RunWith(AndroidJUnit4::class)
    class SearchScreenUiTest {
        @get: Rule
        val composeTestRule = createComposeRule()
        @Before
        fun setUp() {
            composeTestRule.setContent {
                MaterialTheme {
                    FollowingScreen(fakeProfile)
                }
            }
        }
        @Test
        fun testFollowingCards(){
            composeTestRule
                .onAllNodesWithTag("following_card", useUnmergedTree = true)
                .assertCountEquals(3)

            composeTestRule.onNodeWithText("user_3").assertExists()
            composeTestRule.onNodeWithText("user_4").assertExists()
            composeTestRule.onNodeWithText("user_5").assertExists()

            composeTestRule.onNodeWithText("user_2").assertDoesNotExist()
            // then use mock to filter results
        }

        @Test
        fun testUserHeader() {
            composeTestRule.onNodeWithTag("profile_header").assertTextEquals("user_1")
        }
    }

}