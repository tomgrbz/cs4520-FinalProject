package com.example.cs4520_twitter

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.composables.BabFeed
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class BabFeedScreenUiTest {
    @get: Rule
    val composeTestRule = createComposeRule()
    @Before
    fun setUp() {
        composeTestRule.setContent {
            MaterialTheme {
                BabFeed()
            }
        }
    }
    @Test
    fun testBabFeedSetup(){
        // basic components should be on screen
        // composeTestRule.onNodeWithText("@", substring = true).assertExists() // for username of bab post cards
        composeTestRule.onNodeWithText("Babble Feed").assertExists() // screen title
    }
}