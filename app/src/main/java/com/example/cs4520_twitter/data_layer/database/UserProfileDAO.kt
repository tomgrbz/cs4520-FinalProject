package com.example.cs4520_twitter.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * User profile DAO
 */
@Dao
interface UserProfileDao {
    @Insert
    suspend fun insert(userProfile: UserProfileEntity)

    /**
     * Gets profile with given user id (UUID as string)
     */
    @Query("SELECT * FROM user_profiles WHERE user_id = :userID")
    suspend fun getUserProfileById(userID: String): UserProfileEntity?
}