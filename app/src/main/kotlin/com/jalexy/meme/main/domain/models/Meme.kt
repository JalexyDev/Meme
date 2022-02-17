package com.jalexy.meme.main.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meme(
    val id: Int,
    val bottomText: String,
    val image: String,
    val name: String,
    val tags: String,
    val topText: String,
) : Parcelable
