package com.jalexy.meme.main.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jalexy.meme.main.domain.models.MemeSubmission

class Converters {

    @TypeConverter
    fun fromString(value: String): List<MemeSubmission> {
        val listType = object : TypeToken<List<MemeSubmission>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListList(list: List<MemeSubmission>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}