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
    @Query("SELECT * FROM user_profiles WHERE user_userID = :userID")
    suspend fun getUserProfileById(userID: String): UserProfileEntity?

    /**
     * Updates user profile with given userID, editing the description
     */
    @Query("UPDATE user_profiles SET description = :description WHERE user_userID = :userID")
    suspend fun updateDescriptionByUserID(userID: String, description: String)

    /**
     * Updates user profile with given userID, editing the following count and following list
     */
    @Query("UPDATE user_profiles SET followingList = :followingList, followingCount = :followingCount WHERE user_userID = :userID")
    suspend fun updateFollowingListByUserID(
        userID: String,
        followingList: List<UserEntity>,
        followingCount: Int
    )
}