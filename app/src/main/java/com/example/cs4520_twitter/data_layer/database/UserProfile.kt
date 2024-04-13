package com.example.cs4520_twitter.data_layer.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user_profiles")
data class UserProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0, // Stored as String in the database
    @Embedded(prefix = "user_") // need to add prefix to embedded type, since id's clash
    val user: UserEntity,
    val description: String,
    val followerCount: Int,
    val followerList: List<UserEntity>,
    val followingCount: Int,
    val followingList: List<UserEntity>,
    val babCount: Int
)