package com.example.cs4520_twitter

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.UserEntity
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import com.example.cs4520_twitter.data_layer.database.dummyUser
import com.example.cs4520_twitter.data_layer.database.dummyUsername
import java.text.SimpleDateFormat
import java.util.UUID


val fakeUser1 = UserEntity(UUID.randomUUID().toString(), "user_1", "pass", "href")
val fakeUser2 = UserEntity(UUID.randomUUID().toString(), "user_2", "pass", "href")
val fakeUser3 = UserEntity(UUID.randomUUID().toString(), "user_3", "pass", "href")
val fakeUser4 = UserEntity(UUID.randomUUID().toString(), "user_4", "pass", "href")
val fakeUser5 = UserEntity(UUID.randomUUID().toString(), "user_5", "pass", "href")

val fakeProfile: UserProfileEntity = UserProfileEntity(
    1,
    fakeUser1,
    "User1's description",
    2,
    listOf<UserEntity>(fakeUser3, fakeUser2),
    3,
    listOf<UserEntity>(fakeUser3, fakeUser4, fakeUser5),
    2
    )

val fakeBab =
    BabEntity(
        babID = 0,
        authorUser = fakeUser1,
        content = "I made a post!",
        date = SimpleDateFormat("yyyy-MM-dd").parse("2024-04-07"),
        likes = 0,
        likedUserList = mutableListOf()
    )

val fakeUser1Babs = mutableListOf<BabEntity>(fakeBab, fakeBab, fakeBab)