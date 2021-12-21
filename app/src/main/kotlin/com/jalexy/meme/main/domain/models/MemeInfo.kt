package com.jalexy.meme.main.domain.models

data class MemeInfo(
    val id: Int,
    val bottomText: String,
    val details: String,
    val imageUrl: String,
    val name: String,
    val submissions: List<MemeSubmission>,
    val tags: String,
    val thumb: String?,
    val topText: String
)
