package com.example.cs4520_twitter.data_layer.api

import com.example.cs4520_twitter.data_layer.api.models.AddBabRequest
import com.example.cs4520_twitter.data_layer.api.models.AddBabResponse
import com.example.cs4520_twitter.data_layer.api.models.DeleteBabResponse
import com.example.cs4520_twitter.data_layer.api.models.LikesResponse
import com.example.cs4520_twitter.data_layer.api.models.RandomBabsResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface BabApi {

    @POST(Api.BABS_ENDPOINT + "/{babID}/likes")
    suspend fun getUserBabs(@Path("babID") babID: Int, @Body userID: UUID): LikesResponse

    @DELETE(Api.BABS_ENDPOINT + "/{babID}")
    suspend fun deleteBab(@Path("babID") babID: Int): DeleteBabResponse

    @GET(Api.BABS_ENDPOINT)
    suspend fun getRandomBabs(): RandomBabsResponse

    @POST(Api.BABS_ENDPOINT + "/{userID}")
    suspend fun addBab(@Path("userID") userID: UUID, @Body contentBody: AddBabRequest): AddBabResponse
}