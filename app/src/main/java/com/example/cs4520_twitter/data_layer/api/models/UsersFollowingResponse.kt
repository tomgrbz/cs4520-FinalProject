package com.example.cs4520_twitter.data_layer.api.models

import com.example.cs4520_twitter.data_layer.database.UserEntity

data class UsersFollowingResponse(val following: List<UserEntity>, val followingCount: Int)
