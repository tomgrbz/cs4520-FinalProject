package com.example.cs4520_twitter

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.data_layer.database.BabEntity
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Test

var loggedInUserBabFeed = fakeUser1.userID
@RunWith(AndroidJUnit4::class)
class BabFeedScreenVMTest {
    val mockFeedVM = MockBabFeedVM()

    @Test
    fun testInitializedFeedVM() {
        loggedInUserBabFeed = fakeUser1.userID
        assertEquals(mockFeedVM.feed, listOf<BabEntity>()) // just empty initially
    }

    @Test
    fun testFetchRandomBabs() {
        loggedInUserBabFeed = fakeUser1.userID
        mockFeedVM.fetchRandomBabs()
        assertEquals(mockFeedVM.feed, fakeUser1Babs) // feed has babs now
    }

    class MockBabFeedVM() {
        // Mock class of the bab feed screen VM
        var feed = listOf<BabEntity>()
        fun fetchRandomBabs() {
            feed = fakeUser1Babs
        }
    }
}