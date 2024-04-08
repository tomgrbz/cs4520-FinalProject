package com.example.cs4520_twitter.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BabDao {
    @Insert
    suspend fun insert(bab: Bab)

    @Query("SELECT * FROM babs WHERE id = :babId")
    suspend fun getBabById(babId: String): Bab?

    @Query("SELECT * FROM babs WHERE user_id = :userID")
    suspend fun getAllFromUserByUserID(userID: String)
}
