package com.example.cs4520_twitter.repositories

import com.example.cs4520_twitter.data_layer.api.ProfilesApi
import com.example.cs4520_twitter.data_layer.api.UsersApi
import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.UserEntity
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import retrofit2.Response
import retrofit2.http.Path
import java.util.UUID

interface UserProfileRepository {
    suspend fun getUserProfile(userID: String): UserProfileEntity?
    suspend fun updateDescriptionByUserID(userID:String, descr :String)
    suspend fun updateFollowingListByUserID(userID:String, followingList: List<UserEntity>,
                                            followingCount: Int)
}

/**
 * Implementation of Repository that fetch UsersProfile
 */
class UserProfileRepo(private val db : AppDatabase, private val api : ProfilesApi ) : UserProfileRepository {
    private val userProfileDao = db.userProfileDao()

    /** Fetches list of User from Local database*/
    override suspend fun getUserProfile(userID:String) : UserProfileEntity? {

        //return userProfileDao.getUserProfileById(userID)

        val userId: UUID = userID as UUID //(UUID???)
        // Fetch data from third party API
        val remoteResult: Unit = api.getUserProfile(userId)
        when (remoteResult) {
            remoteResult.isSucessful -> {
                // When success replace the local database and return the result
                // You could also return the local data for a single source of truth pattern
                localDataSource.updateUserInfo(remoteResult.data)
                return Result.Success(remoteResult.data)
            }
            is Result.Error -> {
                // If error fallback to local database
                val localResult = localDataSource.getUserInfo()
                when (localResult) {
                    is Result.Success -> {
                        return Result.Success(localResult.data)
                    }
                    is Result.Error -> {
                        return Result.Error(localResult.exception)
                    }
                    is Result.Loading -> {
                        return Result.Loading
                    }
                }
            }
        }
    }
    override suspend fun updateDescriptionByUserID(userID:String, descr :String) {
        return userProfileDao.updateDescriptionByUserID(userID, descr)
    }

    override suspend fun updateFollowingListByUserID(userID:String, followingList: List<UserEntity>,
                                                     followingCount: Int) {
        return userProfileDao.updateFollowingListByUserID(userID, followingList, followingCount)
    }


    private fun whichDataSource() {

    }

}