package com.example.cs4520_twitter.data_layer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val username: String,
    val password: String,
    @ColumnInfo(name = "image_href")
    val imageHref: String,
    )
