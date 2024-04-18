package com.example.cs4520_twitter.repositories

import com.example.cs4520_twitter.data_layer.api.UsersApi
import com.example.cs4520_twitter.data_layer.api.models.RandomBabsResponse
import com.example.cs4520_twitter.data_layer.api.models.UsersFollowingResponse
import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.UserEntity
import retrofit2.http.Path
import java.util.UUID


interface UserRepository {
    suspend fun getUser(userID: String): UserEntity
    suspend fun insertUser(user: UserEntity)

    suspend fun getUsersFollowing(userID: UUID): UsersFollowingResponse

    suspend fun getUserBabs(userID: UUID): RandomBabsResponse
}

/**
 * Implementation of Repository that fetch Users
 */
class UserRepo(private val db : AppDatabase, private val api : UsersApi) : UserRepository {
    private val userDao = db.userDao()

    /** Fetches list of User from Local database*/
      override suspend fun getUser(userID: String) : UserEntity {
         return userDao.getByUserID(userID)
     }

    override suspend fun insertUser(user: UserEntity) {
        userDao.insert(user)
    }

    override suspend fun getUsersFollowing(userID: UUID): UsersFollowingResponse {
        return api.getUsersFollowing(userID)
    }

    override suspend fun getUserBabs(userID: UUID): RandomBabsResponse {
        return api.getUserBabs(userID)
    }

}
