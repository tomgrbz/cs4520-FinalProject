package com.example.cs4520_twitter.data

data class User(
    val userId: String,
    val username: String,
    val imgHref: String,
    val password: String,
)

//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = "users")
//data class User(
//    @PrimaryKey val userId: String,
//    val username: String,
//    val imgHref: String,
//    val password: String,
//)