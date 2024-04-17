package com.example.cs4520_twitter

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.data_layer.database.BabEntity
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Test

var loggedInUserBabFeed = fakeUser1.userID
@RunWith(AndroidJUnit4::class)
class BabFeedScreenVMTest {
    val mockProfileVM = MockProfileVM()

    @Test
    fun testInitializedProfileVM() {
        loggedInUserBabFeed = fakeUser1.userID
        assertEquals(mockProfileVM.loggedUserProfile, "")
        assertEquals(mockProfileVM.loggedUserBabs, listOf<BabEntity>())

        mockProfileVM.fetchLoggedInUserProfile()
        mockProfileVM.fetchLoggedInUserBabs()

        assertEquals(mockProfileVM.loggedUserProfile, loggedInUserBabFeed)
        assertEquals(mockProfileVM.loggedUserBabs, fakeUser1Babs)
    }

    @Test
    fun testProfileVmWithoutLoggedUser() {
        loggedInUserBabFeed = ""
        mockProfileVM.fetchLoggedInUserProfile()
        mockProfileVM.fetchLoggedInUserBabs()
        assertEquals(mockProfileVM.loggedUserProfile, "error")
        assertEquals(mockProfileVM.loggedUserBabs, listOf<BabEntity>())
    }

    class MockProfileVM() {
        // Mock class of the profile screen VM
        public var loggedUserProfile = ""
        public var loggedUserBabs = listOf<BabEntity>()
        fun fetchLoggedInUserProfile()  {
            if (loggedInUserBabFeed.isBlank()) {
                loggedUserProfile = "error"
            } else { loggedUserProfile = loggedInUserBabFeed }
        }

        fun fetchLoggedInUserBabs() {
            if (loggedInUserBabFeed.isNotBlank()) {
                loggedUserBabs = fakeUser1Babs
            } else {
                listOf<BabEntity>()
            }
        }
    }
}