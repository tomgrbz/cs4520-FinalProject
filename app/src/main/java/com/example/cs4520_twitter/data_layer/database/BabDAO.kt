package com.example.cs4520_twitter.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Bab DAO
 */
@Dao
interface BabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bab: BabEntity)

    /**
     * Get bab given specified bab id
     */
    @Query("SELECT * FROM babs WHERE babID = :babId")
    suspend fun getBabById(babId: String): BabEntity?

    @Query("SELECT * FROM babs")
    suspend fun getAllBabs(): List<BabEntity>?

    /**
     * Gets all babs associated with given user id
     */
    @Query("SELECT * FROM babs WHERE user_userID = :userID")
    suspend fun getAllFromUserByUserID(userID: String): List<BabEntity?>

    @Query("DELETE FROM babs WHERE babID = :babId")
    suspend fun deleteById(babId: String)

    @Query("SELECT * FROM babs WHERE content LIKE :searchQuery")
    suspend fun searchBabsWithString(searchQuery: String): List<BabEntity?>
}
