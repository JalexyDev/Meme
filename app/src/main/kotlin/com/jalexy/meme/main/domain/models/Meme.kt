package com.jalexy.meme.main.domain.models

import android.os.Parcelable
import android.widget.ListAdapter
import com.jalexy.meme.memelist.presentation.ListItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meme(
    var isFavorite: Boolean,
    val id: Int,
    val bottomText: String,
    val image: String,
    val name: String,
    val tags: String,
    val topText: String,
) : Parcelable, ListItem {

    override fun getItemId(): Int = id
}