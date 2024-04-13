package com.example.cs4520_twitter.data_layer.api

import com.example.cs4520_twitter.data_layer.api.models.FollowResponse
import com.example.cs4520_twitter.data_layer.api.models.UnfollowResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface FollowsApi {

    @POST(Api.FOLLOWS_ENDPOINT + "/{toFollowUserID}")
    suspend fun followUser(@Path("toFollowUserID") toFollowUserID: UUID, @Body thisUserId: UUID): FollowResponse

    @DELETE(Api.FOLLOWS_ENDPOINT + "{toDeleteUserID}")
    suspend fun unfollowUser(@Path("toDeleteUserID") toDeleteUserID: UUID, @Body thisUserId: UUID): UnfollowResponse
}