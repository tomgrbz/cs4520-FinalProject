package com.example.cs4520_twitter.repositories

import org.junit.Assert.*
import com.example.cs4520_twitter.data_layer.api.ProfilesApi
import com.example.cs4520_twitter.data_layer.api.models.GetProfileResponse
import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.UserEntity
import com.example.cs4520_twitter.data_layer.database.UserProfileDao
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

    class UserProfileRepoTest {
        // Mocks
        private val mockDb = mock(AppDatabase::class.java)
        private val mockApi = mock(ProfilesApi::class.java)
        private val mockUserProfileDao = mock(UserProfileDao::class.java)

        // Create an instance of UserProfileRepo with mock objects
        private val userProfileRepo = UserProfileRepo(mockDb, mockApi)

        private val mockUsers = listOf(
            UserEntity("user1", "User 1", "123", "123"),
            UserEntity("user2", "User 2", "123", "123"),
            UserEntity("user3", "User 3", "123", "123")
        )

        private val mockProfile = listOf(
            UserProfileEntity(1, mockUsers[0], "123", 2, mockUsers, 1,mockUsers, 1),
        )

        val babs =
            listOf(
                BabEntity(1, mockUsers[0], " ", "12/3" as Date, 1, userIDs ),
                BabEntity(2, mockUsers[1], " ", "12/3" as Date, 1, userIDs),
                BabEntity(3, mockUsers[2], " ", "12/3" as Date, 1, userIDs)
            )
        private val userIDs = listOf("1", "2", "3" )

        @Test
        fun `test getUserProfile should return user profile`() {
            val userId = UUID.randomUUID().toString()
            val expectedProfile = mockProfile
            val apiResult = GetProfileResponse(expectedProfile)
            `when`(mockApi.getUserProfile(UUID.fromString(userId))).thenReturn(apiResult)

            val result = userProfileRepo.getUserProfile(userId)

            assertEquals(expectedProfile, result)
        }

        @Test
        fun `test getUserProfile should return user profile from db`() {
            val userId = UUID.randomUUID().toString()
            val expectedProfile = mockProfile
            `when`(mockApi.getUserProfile(UUID.fromString(userId))).thenThrow(Exception("API call failed"))
            `when`(mockUserProfileDao.getUserProfileById(userId)).thenReturn(expectedProfile)

            val result = userProfileRepo.getUserProfile(userId)

            assertEquals(expectedProfile, result)
        }

        @Test(expected = Exception::class)
        fun `test getUserProfile should throw exception`() {
            val userId = UUID.randomUUID().toString()
            `when`(mockApi.getUserProfile(UUID.fromString(userId))).thenThrow(Exception("API call failed"))
            `when`(mockUserProfileDao.getUserProfileById(userId)).thenReturn(null)

            userProfileRepo.getUserProfile(userId)

        }
}