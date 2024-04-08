package com.example.cs4520_twitter.data_layer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cs4520_twitter.utils.UserEntityTypeConverter

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(UserEntityTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    abstract fun userProfileDao(): UserProfileDao

    abstract fun babDao(): BabDao
}
