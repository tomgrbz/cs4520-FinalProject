package com.example.cs4520_twitter.following

import androidx.lifecycle.ViewModel
import com.example.cs4520_twitter.data_layer.database.UserEntity

class FollowingScreenViewModel: ViewModel() {
    private val _following: MutableList<UserEntity> = mutableListOf(
        UserEntity("123", "user_1", "pw", "href"),
        UserEntity("123", "user_1", "pw", "href"),
        UserEntity("123", "user_1", "pw", "href"),
        UserEntity("123", "user_1", "pw", "href"),
        UserEntity("123", "user_1", "pw", "href"),
        UserEntity("123", "user_1", "pw", "href"),
        UserEntity("123", "user_1", "pw", "href"),
        UserEntity("123", "user_1", "pw", "href"),
        )
    val following: MutableList<UserEntity> = _following
    fun unfollow(userId: String) {

    }

    fun loadUserFollowing(userId: String) {

    }
}