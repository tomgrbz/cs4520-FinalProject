package com.example.cs4520_twitter.data_layer.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "babs")
data class Bab(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Stored as String in the database
    @Embedded(prefix = "user_")
    val authorUser: UserEntity,
    val content: String,
    val date: Date,
    val likes: Int,
    val likedUserList: List<String> // List of User Ids
)

