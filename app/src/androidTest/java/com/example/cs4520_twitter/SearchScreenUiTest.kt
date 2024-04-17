package com.example.cs4520_twitter

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.composables.SearchScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class SearchScreenUiTest {
    @RunWith(AndroidJUnit4::class)
    class SearchScreenUiTest {
        @get: Rule
        val composeTestRule = createComposeRule()
        @Before
        fun setUp() {
            composeTestRule.setContent {
                MaterialTheme {
                    SearchScreen()
                }
            }
        }
        @Test
        fun testSearchResultsRender(){
            composeTestRule
                .onNodeWithText("Search Babble")
                .assertExists()

            composeTestRule
                .onNodeWithTag("search_button")
                .assertExists()

            composeTestRule
                .onNodeWithText("Search Babble")
                .performClick()
                .performTextInput("Bab2")

            composeTestRule
                .onNodeWithTag("search_button")
                .assertHasClickAction()

            // then use mock to filter results
        }
    }

}