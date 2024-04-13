package com.example.cs4520_twitter.data_layer.api.models

import com.example.cs4520_twitter.data_layer.database.UserProfileEntity

data class FollowResponse(val success: Boolean, val toFollowUserProfile: UserProfileEntity, val thisUserProfile: UserProfileEntity)