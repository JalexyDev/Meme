package com.jalexy.meme.main.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meme_items")
data class MemeDbModel(
    @PrimaryKey
    val id: Int,
    val bottomText: String,
    val image: String,
    val name: String,
    val tags: String,
    val topText: String,
    var isFavorite: Boolean
)