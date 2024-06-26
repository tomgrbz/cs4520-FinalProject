package com.example.cs4520_twitter.data_layer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cs4520_twitter.utils.UserEntityTypeConverter

/**
 * Room DB instance for Babble
 * Has 3 main tables: User, UserProfile, and Babs
 *
 * Provides singleton method of getting an instance handle
 * of the DB
 */
@Database(entities = [UserEntity::class, UserProfileEntity::class, BabEntity::class], version = 1)
@TypeConverters(UserEntityTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    abstract fun userProfileDao(): UserProfileDao

    abstract fun babDao(): BabDao

    companion object {

        @Volatile
        private var dbInstance: AppDatabase? = null


        fun instance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = dbInstance

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "babbleDB"
                    ).build()
                    dbInstance = instance

                }
                return instance
            }
        }
    }
}
