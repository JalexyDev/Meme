package com.jalexy.meme.main.data.database

import androidx.room.*
import com.jalexy.meme.main.domain.models.MemeSubmission

@Entity(tableName = "info_items")
data class MemeInfoDBModel(
    @PrimaryKey
    val id: Int,
    val bottomText: String,
    val details: String,
    val imageUrl: String,
    val name: String,
    @TypeConverters(Converters::class)
    val submissions: List<MemeSubmission>,
    val tags: String,
    val thumb: String?,
    val topText: String
)
