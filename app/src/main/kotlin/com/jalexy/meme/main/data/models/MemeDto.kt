package com.jalexy.meme.main.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MemeDto(

    @SerializedName("ID")
    @Expose
    val id: Int,
    @SerializedName("bottomText")
    @Expose
    val bottomText: String?,
    @SerializedName("image")
    @Expose
    val image: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("tags")
    @Expose
    val tags: String?,
    @SerializedName("topText")
    @Expose
    val topText: String?
)
