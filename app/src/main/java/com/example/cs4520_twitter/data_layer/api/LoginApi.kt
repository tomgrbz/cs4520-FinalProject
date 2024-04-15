package com.example.cs4520_twitter.data_layer.api

import com.example.cs4520_twitter.data_layer.api.models.CredentialsPostRequest
import com.example.cs4520_twitter.data_layer.api.models.CredentialsResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST(Api.LOGIN_ENDPOINT)
    suspend fun login(@Body requestBody: CredentialsPostRequest): CredentialsResponse
}