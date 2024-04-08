package com.example.cs4520_twitter.data_layer.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Stored as String in the database
    @Embedded(prefix = "user_")
    val user: UserEntity,
    val description: String,
    val followerCount: Int,
    val followerList: List<UserEntity>,
    val followingCount: Int,
    val followingList: List<UserEntity>,
    val babCount: Int
)