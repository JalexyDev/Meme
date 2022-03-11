package com.jalexy.meme.main.domain.models

import androidx.room.Entity

data class MemeSubmission(
    val bottomText: String,
    val dateCreated: String,
    val topText: String
)
