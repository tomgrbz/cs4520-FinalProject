package com.example.cs4520_twitter.repositories

import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.BabEntity

interface BabRepository {
    suspend fun getBab(babID: String): BabEntity?
}

/**
 * Implementation of Repository that fetch UsersProfile
 */
class BabRepo(private val db : AppDatabase) : BabRepository {

    /** Fetches list of User from Local database*/
    override suspend fun getBab(babID:String) : BabEntity? {
        val babDao = db.babDao()
        return babDao.getBabById(babID)
    }
}

