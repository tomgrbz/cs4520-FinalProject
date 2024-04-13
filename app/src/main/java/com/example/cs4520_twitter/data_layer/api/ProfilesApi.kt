package com.example.cs4520_twitter.data_layer.api

import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface ProfilesApi {

    @GET(Api.PROFILES_ENDPOINT + "/{userID}")
    suspend fun getUserProfile(@Path("userID") userID: UUID)
}