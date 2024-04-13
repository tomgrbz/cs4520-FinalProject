package com.example.cs4520_twitter.data_layer.api

import com.example.cs4520_twitter.data_layer.api.models.CredentialsPostRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApi {

    @POST(Api.SIGNUP_ENDPOINT)
    suspend fun signup(@Body requestBody: CredentialsPostRequest)

}