package com.example.cs4520_twitter.repositories

import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity

interface UserProfileRepository {
    suspend fun getUserProfile(userID: String): UserProfileEntity?
}

/**
 * Implementation of Repository that fetch UsersProfile
 */
class UserProfileRepo(private val db : AppDatabase) : UserProfileRepository {

    /** Fetches list of User from Local database*/
    override suspend fun getUserProfile(userID:String) : UserProfileEntity? {
        val userProfileDao = db.userProfileDao()
        return userProfileDao.getUserProfileById(userID)
    }
}