package com.example.cs4520_twitter.data_layer.database

import java.text.SimpleDateFormat

// Data for testing with screen composables

// Dummy data with Entity datatype
const val dummyUsername = "babble_user"
val dummyUser = UserEntity("_", dummyUsername, "", "password")
val dummyProfile = UserProfileEntity(id = 0,
    user = dummyUser,
    description = "Hello!",
    followerCount = 5000,
    followerList = mutableListOf<UserEntity>(),
    followingCount = 3,
    followingList = mutableListOf<UserEntity>(),
    babCount = 5)
val dummyBab = BabEntity(
    babID = 0,
    authorUser = dummyUser,
    content = "I made a post!",
    date = SimpleDateFormat("yyyy-MM-dd").parse("2024-04-07"),
    likes = 3,
    likedUserList = mutableListOf(dummyUsername))
val dummyImageURL = "https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg"
val dummyBabList = mutableListOf(dummyBab, dummyBab, dummyBab, dummyBab, dummyBab)