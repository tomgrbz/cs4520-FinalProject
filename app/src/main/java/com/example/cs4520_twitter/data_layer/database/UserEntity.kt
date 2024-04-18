package com.example.cs4520_twitter.data_layer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
data class UserEntity(
    /**
     * Need to convert to string as Room DB does not have native
     * support for UUID :(
     */
    @PrimaryKey
    val userID: String = UUID.randomUUID().toString(),
    val username: String,
    val password: String,
    val imgHref: String,
)
