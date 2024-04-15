package com.example.cs4520_twitter.application

import android.content.Context
import androidx.room.Room
import com.example.cs4520_twitter.data_layer.api.Api
import com.example.cs4520_twitter.data_layer.database.AppDatabase

/**
 * AppContainer that is shareable across view models for fetching data or updating data
 * via specific endpoints. Also holds on to local data source of the BabsDatabase
 */
class AppContainer {

    val babsService = Api.babsApiService

    val followsApiService = Api.followsApiService

    val loginApiService = Api.loginApiService

    val signupApiService = Api.signupApiService

    val profilesApiService = Api.profilesApiService

    val usersApiService = Api.usersApiService

    // Client for local room database
    private lateinit var localDataSource: AppDatabase

    /**
     * Example of how to define repos
     *
    lateinit var usersRepo: UserRepo

     */
    // If there is already a ProductRepository instance, as Singleton representation
    private var instance: Boolean = false

    fun createLocalDataSource(context: Context) {
        localDataSource =
            Room.databaseBuilder(context, AppDatabase::class.java, "babbleDB").build()
    }

//    fun createProductRepository(context: Context) {
//        if (!instance && this::localDataSource.isInitialized) {
//            productRepository = ProductRepository(remoteDataSource, localDataSource, context)
//            instance = true
//        }
//    }
}