package com.example.cs4520_twitter.data_layer.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "babs")
data class BabEntity(
    @PrimaryKey(autoGenerate = true)
    val babID: Int = 0, // Stored as String in the database
    @Embedded(prefix = "user_") // need to add prefix to embedded type, since id's clash
    val authorUser: UserEntity,
    val content: String,
    val date: Date,
    val likes: Int,
    val likedUserList: List<String> // List of User Ids
)

