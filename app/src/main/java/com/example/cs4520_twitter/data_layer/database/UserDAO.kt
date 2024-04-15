package com.example.cs4520_twitter.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * User DAO
 */
@Dao
interface UserDAO {

    /**
     * Gets user object with given user id (UUID as string)
     */
    @Query("SELECT * FROM users WHERE userID = :userID")
    suspend fun getByUserID(userID: String): UserEntity

    @Insert
    suspend fun insert(user: UserEntity)
}