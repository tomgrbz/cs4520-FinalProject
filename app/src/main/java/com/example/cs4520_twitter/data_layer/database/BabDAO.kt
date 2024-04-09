package com.example.cs4520_twitter.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Bab DAO
 */
@Dao
interface BabDao {
    @Insert
    suspend fun insert(bab: BabEntity)

    /**
     * Get bab given specified bab id
     */
    @Query("SELECT * FROM babs WHERE id = :babId")
    suspend fun getBabById(babId: String): BabEntity?

    /**
     * Gets all babs associated with given user id
     */
    @Query("SELECT * FROM babs WHERE user_id = :userID")
    suspend fun getAllFromUserByUserID(userID: String)

    @Query("DELETE FROM babs WHERE id = :babId")
    suspend fun deleteById(babId: String)
}
