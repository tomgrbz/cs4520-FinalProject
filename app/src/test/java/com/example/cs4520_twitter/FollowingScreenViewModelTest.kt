package com.example.cs4520_twitter

import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.data.User
import com.example.cs4520_twitter.data_layer.api.FollowsApi
import com.example.cs4520_twitter.data_layer.api.ProfilesApi
import com.example.cs4520_twitter.data_layer.api.UsersApi
import com.example.cs4520_twitter.data_layer.api.models.FollowResponse
import com.example.cs4520_twitter.data_layer.api.models.GetProfileResponse
import com.example.cs4520_twitter.data_layer.api.models.RandomBabsResponse
import com.example.cs4520_twitter.data_layer.api.models.UnfollowResponse
import com.example.cs4520_twitter.data_layer.api.models.UsersFollowingResponse
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.UserEntity
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import com.example.cs4520_twitter.vms.FollowingScreenViewModel
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import java.time.Instant
import java.util.Date
import java.util.UUID
import kotlin.random.Random

class FollowingScreenViewModelTest {

    private val mockUserApi = MockUserApi()
    private val mockFollowApi = MockFollowApi()
    private val mockProfilesApi = MockProfileApi()
    private val underTest = FollowingScreenViewModel(followApi = mockFollowApi, profilesApi = mockProfilesApi, userApi = mockUserApi)

    @Before
    fun resetLogginUserID() {
        LoggedInUser.loggedInUserId = ""
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
}

class MockUserApi: UsersApi {
    override suspend fun getUserBabs(userID: UUID): RandomBabsResponse {
        return RandomBabsResponse(listOf<BabEntity>(
            BabEntity(
                0,
                UserEntity(
                    "10",
                    "test",
                    "password",
                    "res/bear",
                ),
                "random content",
                Date.from(Instant.now()),
                0,
                emptyList()
            )
        ))
    }

    override suspend fun getUsersFollowing(userID: UUID): UsersFollowingResponse {
        return UsersFollowingResponse(
            listOf(
                UserEntity(
                    "10",
                    "test",
                    "password",
                    "res/bear",
                )
            ), 0
        )
    }
}

class MockFollowApi: FollowsApi {
    override suspend fun followUser(toFollowUserID: UUID, thisUserId: UUID): FollowResponse {
        return FollowResponse(
            success = true,
            generateUserProfile(0, "Test1", "Test1", "pass"),
            generateUserProfile(1, "Test2", "Test2", "pass")
        )
    }

    override suspend fun unfollowUser(toDeleteUserID: UUID, thisUserId: UUID): UnfollowResponse {
        return UnfollowResponse(
            success = true,
            generateUserProfile(0, null, "Test1", "pass"),
            generateUserProfile(1, null, "Test2", "pass")
        )
    }

}

class MockProfileApi: ProfilesApi {
    override suspend fun getUserProfile(userID: UUID): GetProfileResponse {
        return GetProfileResponse(
            generateUserProfile(0, null, "Test1", "pass")
        )
    }

}


// Function to generate a UserProfileEntity with specified id, username, and password
fun generateUserProfile(id: Int? = null, userId: String? = null, username: String? = null, password: String? = null): UserProfileEntity {
    val user = UserEntity(
        userID = userId ?: UUID.randomUUID().toString(),
        username = username ?: "testUser${id ?: Random.nextInt(1, 100)}",
        password = password ?: "password${id ?: Random.nextInt(1, 100)}",
        imgHref = "https://example.com/avatar/${userId ?: UUID.randomUUID().toString()}.png"
    )

    val followerCount = Random.nextInt(0, 100)
    val followingCount = Random.nextInt(0, 100)
    val babCount = Random.nextInt(0, 50)

    // Generate random lists of followers and following
    val followers = List(followerCount) { generateRandomUser("follower", it + 1) }
    val followings = List(followingCount) { generateRandomUser("following", it + 1) }

    return UserProfileEntity(
        id = id,
        user = user,
        description = "This is a randomly generated profile for testing.",
        followerCount = followerCount,
        followerList = followers,
        followingCount = followingCount,
        followingList = followings,
        babCount = babCount
    )
}

// Helper function to generate a random UserEntity
private fun generateRandomUser(prefix: String, id: Int): UserEntity {
    return UserEntity(
        userID = UUID.randomUUID().toString(),
        username = "$prefix-user$id",
        password = "password$id",
        imgHref = "https://example.com/avatar/$id.png"
    )
}}