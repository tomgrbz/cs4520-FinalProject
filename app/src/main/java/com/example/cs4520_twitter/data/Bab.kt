package com.example.cs4520_twitter.data

import java.util.Date

data class Bab(
    val id: String,
    val authorUser: User,
    val content: String,
    val date: Date,
    val likes: Integer,
    val likedUserList: List<String>
)
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import java.util.Date
//
//@Entity(tableName = "babs")
//data class Bab(
//    @PrimaryKey val id: String,
//    val authorUser: User,
//    val content: String,
//    val date: Date,
//    val likes: Integer,
//    val likedUserList: List<String>
//)
