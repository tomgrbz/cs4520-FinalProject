package com.example.cs4520_twitter.data_layer.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDAO {

    @Query("SELECT * FROM users WHERE id = :userID")
    suspend fun getByUserID(userID: String)
}