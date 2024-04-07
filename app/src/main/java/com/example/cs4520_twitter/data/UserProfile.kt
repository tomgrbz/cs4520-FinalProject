package com.example.cs4520_twitter.data

data class UserProfile(
    val id: String,
    val user: User,
    val description: String,
    val followerCount: Int,
    val followerList: List<User>,
    val followingCount: Int,
    val followingList: List<User>,
    val babCount: Int,
)
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = "profiles")
//data class UserProfile(
//    @PrimaryKey val id: String,
//    val user: User,
//    val description: String,
//    val followerCount: Number,
//    val followerList: List<User>,
//    val followingCount: Number,
//    val followingList: List<User>,
//    val babCount: Number,
//)
