package com.example.cs4520_twitter.data_layer.api

import com.example.cs4520_twitter.data_layer.api.models.EditDescriptionRequest
import com.example.cs4520_twitter.data_layer.api.models.EditUsernameRequest
import com.example.cs4520_twitter.data_layer.api.models.GetProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface ProfilesApi {
    @GET(Api.PROFILES_ENDPOINT + "/{userID}")
    suspend fun getUserProfile(@Path("userID") userID: UUID): GetProfileResponse

    @POST(Api.PROFILES_ENDPOINT + "/{userID}/description")
    suspend fun changeUserProfileDescription(
        @Path("userID") userID: UUID,
        @Body description: EditDescriptionRequest
    ): GetProfileResponse

    @POST(Api.PROFILES_ENDPOINT + "/{userID}/username")
    suspend fun changeUserProfileName(
        @Path("userID") userID: UUID,
        @Body username: EditUsernameRequest
    ): GetProfileResponse
}