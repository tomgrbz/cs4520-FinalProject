package com.example.cs4520_twitter.data_layer.api.models

import com.example.cs4520_twitter.data_layer.database.UserProfileEntity

data class UnfollowResponse(
    val success: Boolean,
    val toUnfollowUserProfile: UserProfileEntity,
    val thisUserProfile: UserProfileEntity
)