package com.example.cs4520_twitter.data_layer.api

import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface UsersApi {

    @GET(Api.USERS_ENDPOINT + "/babs/{userID}")
    suspend fun getUserBabs(@Path("userID") userID: UUID)

    @GET(Api.USERS_ENDPOINT + "/{userID}/following")
    suspend fun getUsersFollowing(@Path("userID") userID: UUID)
}