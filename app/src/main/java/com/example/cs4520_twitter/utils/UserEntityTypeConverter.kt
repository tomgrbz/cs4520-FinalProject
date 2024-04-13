package com.example.cs4520_twitter.utils

import androidx.room.TypeConverter
import com.example.cs4520_twitter.data_layer.database.UserEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date
import java.util.UUID

/**
 * Type converter class used by RoomDB for entity fields of Date and UUID
 */
class UserEntityTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromUserList(value: String?): List<UserEntity>? {
        val listType = object : TypeToken<List<UserEntity>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun userListToString(list: List<UserEntity>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time
    }


}
