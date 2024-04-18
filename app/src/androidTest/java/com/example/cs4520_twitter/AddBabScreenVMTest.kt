package com.example.cs4520_twitter

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cs4520_twitter.data_layer.database.BabEntity
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.UUID

var loggedInUserAddBab = fakeUser1.userID
@RunWith(AndroidJUnit4::class)
class AddBabScreenVMTest {
    var mockAddBabVM = MockAddBabVM()

    @Test
    fun testInitializedAddBabVM() {
        mockAddBabVM = MockAddBabVM()
        loggedInUserAddBab = fakeUser1.userID
        assertEquals(mockAddBabVM.loggedUserProfile, "")
        assertEquals(mockAddBabVM.loggedUserBabs, mutableListOf<BabEntity>())

        mockAddBabVM.fetchLoggedInUserProfile()

        assertEquals(mockAddBabVM.loggedUserProfile, loggedInUserAddBab)
        assertEquals(mockAddBabVM.loggedUserBabs, fakeUser1Babs)
    }

    @Test
    fun testAddBabVmWithoutLoggedUser() {
        loggedInUserAddBab = ""
        mockAddBabVM.fetchLoggedInUserProfile()
        assertEquals(mockAddBabVM.loggedUserProfile, "error")
        assertEquals(mockAddBabVM.loggedUserBabs, listOf<BabEntity>())
    }

    @Test
    fun testAddingBab() { // when adding a bab
        loggedInUserAddBab = fakeUser1.userID
        mockAddBabVM.fetchLoggedInUserProfile()
        assertEquals(mockAddBabVM.loggedUserBabs.size, 3)

        mockAddBabVM.addBabForLoggedInUser("Hello!")
        val userBabs = mockAddBabVM.loggedUserBabs
        assertEquals(userBabs.size, 4) // size of list increased by 1
        assertEquals(userBabs[3].babID, 1)
        assertEquals(userBabs[3].authorUser, fakeUser1)
        assertEquals(userBabs[3].content, "Hello!")
        assertEquals(userBabs[3].date, SimpleDateFormat("yyyy-MM-dd").parse("2024-04-07"))
        assertEquals(userBabs[3].likedUserList, mutableListOf<String>())
        assertEquals(userBabs[3].likes, 0)
    }

    class MockAddBabVM() {
        // Mock class of the profile screen VM
        var loggedUserProfile = ""
        var loggedUserBabs = mutableListOf<BabEntity>()
        fun fetchLoggedInUserProfile()  {
            if (loggedInUserAddBab.isBlank()) {
                loggedUserProfile = "error"
            } else { loggedUserProfile = loggedInUserAddBab
                loggedUserBabs = fakeUser1Babs}
        }
        fun addBabForLoggedInUser(content : String) {
            loggedUserBabs.add(BabEntity(1,
                fakeUser1,
                content,
                SimpleDateFormat("yyyy-MM-dd").parse("2024-04-07"),
                0,
                mutableListOf()))
        }
    }
}