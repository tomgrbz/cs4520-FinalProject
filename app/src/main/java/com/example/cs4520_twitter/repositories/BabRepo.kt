package com.example.cs4520_twitter.repositories

import com.example.cs4520_twitter.data_layer.api.BabApi
import com.example.cs4520_twitter.data_layer.api.models.AddBabResponse
import com.example.cs4520_twitter.data_layer.api.models.LikesResponse
import com.example.cs4520_twitter.data_layer.api.models.RandomBabsResponse
import com.example.cs4520_twitter.data_layer.database.AppDatabase
import com.example.cs4520_twitter.data_layer.database.BabEntity
import retrofit2.http.Body
import retrofit2.http.Path
import java.util.UUID

interface BabRepository {
    suspend fun getBab(babId: String): BabEntity?

    suspend fun insertBab(bab: BabEntity)

    suspend fun getAllBabsByUser(userID: String): List<BabEntity?>

    suspend fun deleteBabById(babId: String)

    suspend fun searchBabsWithString(searchQuery: String): List<BabEntity?>

    suspend fun getRandomBabs(): RandomBabsResponse

    suspend fun addBab(userID: UUID, content: String): AddBabResponse

    suspend fun getUserBabs(babID: Int, userID: UUID): LikesResponse
}

/**
 * Implementation of Repository that fetch UsersProfile
 */
class BabRepo(private val db : AppDatabase, private val api :BabApi ) : BabRepository {
    private val babDao = db.babDao()


    override suspend fun getBab(babId:String) : BabEntity? {
        return babDao.getBabById(babId)
    }

    override suspend fun insertBab(bab: BabEntity) {
        return babDao.insert(bab)
    }

    override suspend fun getAllBabsByUser(userID: String): List<BabEntity?> {
        return babDao.getAllFromUserByUserID(userID)
    }

    override suspend fun deleteBabById(babId: String) {
        try {
            val apiResult = api.deleteBab(babId.toInt())

            if (!apiResult.success) {
                babDao.deleteById(babId)
            }
        } catch (e: Exception) {
            // Do Nothing
        }
    }

    override suspend fun searchBabsWithString(searchQuery: String): List<BabEntity?> {
        return babDao.searchBabsWithString(searchQuery)
    }

    override suspend fun getRandomBabs(): RandomBabsResponse {
        return api.getRandomBabs()
    }

    override suspend fun addBab(userID: UUID, content: String): AddBabResponse {
        return api.addBab(userID, content)
    }

    override suspend fun getUserBabs(babID: Int, userID: UUID): LikesResponse {
        return api.getUserBabs(babID, userID)
    }
}