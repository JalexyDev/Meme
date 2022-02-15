package com.jalexy.meme.main.domain.models

data class Meme(
    val id: Int,
    val bottomText: String,
    val image: String,
    val name: String,
    val tags: String,
    val topText: String
)
