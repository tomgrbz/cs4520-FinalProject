package com.example.cs4520_twitter.repositories
import com.example.cs4520_twitter.data_layer.api.UsersApi
import com.example.cs4520_twitter.data_layer.api.models.RandomBabsResponse
import com.example.cs4520_twitter.data_layer.api.models.UsersFollowingResponse
import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.UserEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

class UserRepoTest {

    // creating mock
    private val mockDb = mock(AppDatabase::class.java)
    private val mockApi = mock(UsersApi::class.java)

    // Create an instance of UserRepo with mock objects
    private val userRepo = UserRepo(mockDb, mockApi)
    private val mockUsers = listOf(
    UserEntity("user1", "User 1", "123", "123"),
    UserEntity("user2", "User 2", "123", "123"),
    UserEntity("user3", "User 3", "123", "123")
    )

    val babs =
        listOf(
            BabEntity(1, mockUsers[0], " ", "12/3" as Date, 1, userIDs ),
            BabEntity(2, mockUsers[1], " ", "12/3" as Date, 1, userIDs),
            BabEntity(3, mockUsers[2], " ", "12/3" as Date, 1, userIDs)
        )
    private val userIDs = listOf("1", "2", "3" )

    val followingResponse = UsersFollowingResponse(mockUsers, 3)


    @Test
    fun `test getUser should return user from database`() {
        val userId = "user1"
        val expectedUser = UserEntity(userId, " User 1", "123", "123")
        `when`(mockDb.userDao().getByUserID(userId)).thenReturn(expectedUser)

        val result = userRepo.getUser(userId)

        assertEquals(expectedUser, result)
    }

    @Test
    fun `test insertUser should insert user into database`() {
        val userToInsert = UserEntity("user1", " User 1", "123", "123")

        userRepo.insertUser(userToInsert)

        verify(mockDb.userDao()).insert(userToInsert)
    }

    @Test
    fun `test getUsersFollowing should return response `() {
        val userId = UUID.randomUUID()
        val expectedResponse = followingResponse
        `when`(mockApi.getUsersFollowing(userId)).thenReturn(expectedResponse)

        val result = userRepo.getUsersFollowing(userId)

        assertEquals(expectedResponse, result)
    }

    @Test
    fun `test getUserBabs should return response from API`() {
        val userId = UUID.randomUUID()
        val expectedResponse = babs
        `when`(mockApi.getUserBabs(userId)).thenReturn(expectedResponse)

        val result = userRepo.getUserBabs(userId)

        assertEquals(expectedResponse, result)
    }
}