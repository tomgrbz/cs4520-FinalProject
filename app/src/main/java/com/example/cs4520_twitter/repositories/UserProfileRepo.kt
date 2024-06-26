package com.example.cs4520_twitter.repositories

import com.example.cs4520_twitter.data_layer.api.ProfilesApi
import com.example.cs4520_twitter.data_layer.api.models.GetProfileResponse
import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.UserEntity
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import retrofit2.Response
import java.util.UUID

// User Profile Repository
interface UserProfileRepository {
    suspend fun getUserProfile(userID: String): UserProfileEntity?
    suspend fun updateDescriptionByUserID(userID:String, descr :String)
    suspend fun updateFollowingListByUserID(userID:String, followingList: List<UserEntity>,
                                            followingCount: Int)
    suspend fun insertUserProfile(userProfile: UserProfileEntity?)
}

/**
 * Implementation of Repository that fetch UsersProfile
 */
class UserProfileRepo(private val db : AppDatabase, private val api : ProfilesApi ) : UserProfileRepository {
    private val userProfileDao = db.userProfileDao()

    // Fetches a user local or remotely
    override suspend fun getUserProfile(userID:String) : UserProfileEntity? {
        return try {
            val userId = UUID.fromString(userID)
            val apiResult: GetProfileResponse = api.getUserProfile(userId)

            if (apiResult != null) {
                this.insertUserProfile(apiResult.profile)
                apiResult.profile // Assuming the body contains UserProfileEntity
            } else {
                // if API Call does not work
                val response = userProfileDao.getUserProfileById(userID)
                this.insertUserProfile(response)
                userProfileDao.getUserProfileById(userID)
            }

        } catch (e: Exception) {
            throw Exception("Cannot get User Profile")
        }
    }

    // Updates a description via UserID
    override suspend fun updateDescriptionByUserID(userID:String, descr :String) {
         userProfileDao.updateDescriptionByUserID(userID, descr)
    }

    // Updates a Users Following list
    override suspend fun updateFollowingListByUserID(userID:String, followingList: List<UserEntity>,
                                                     followingCount: Int) {
         userProfileDao.updateFollowingListByUserID(userID, followingList, followingCount)
    }

    // Inserts a user profile into the local database
    override suspend fun insertUserProfile(userProfile: UserProfileEntity?) {
        userProfileDao.insert(userProfile as UserProfileEntity)
    }

}