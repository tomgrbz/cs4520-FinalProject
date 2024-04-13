package com.example.cs4520_twitter.data_layer.api

import com.example.cs4520_twitter.data_layer.api.models.UsersFollowingResponse
import com.example.cs4520_twitter.data_layer.database.BabEntity
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface UsersApi {

    @GET(Api.USERS_ENDPOINT + "/babs/{userID}")
    suspend fun getUserBabs(@Path("userID") userID: UUID): List<BabEntity>

    @GET(Api.USERS_ENDPOINT + "/{userID}/following")
    suspend fun getUsersFollowing(@Path("userID") userID: UUID): UsersFollowingResponse
}