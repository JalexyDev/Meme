package com.jalexy.meme.main.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MemeInfoDto(
    @SerializedName("ID")
    @Expose
    val id: Int,
    @SerializedName("bottomText")
    @Expose
    val bottomText: String,
    @SerializedName("detail")
    @Expose
    val details: String,
    @SerializedName("image")
    @Expose
    val imageUrl: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("submissions")
    @Expose
    val submissions: List<MemeSubmissionDto>,
    @SerializedName("tags")
    @Expose
    val tags: String,
    @SerializedName("thumb")
    @Expose
    val thumb: String,
    @SerializedName("topText")
    @Expose
    val topText: String
)
