package com.example.cs4520_twitter.repositories

import android.content.Context
import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.UserEntity


interface UserRepository {
    suspend fun getUser(userID: String): UserEntity
}

/**
 * Implementation of Repository that fetch Users
 */
class UserRepo(private val db : AppDatabase) : UserRepository {

    /** Fetches list of User from Local database*/
      override suspend fun getUser(userID: String) : UserEntity {
         val userDao = db.userDao()
         return userDao.getByUserID(userID)
     }
}
