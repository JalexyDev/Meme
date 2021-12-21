package com.jalexy.meme.main.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MemeSubmissionDto(
    @SerializedName("bottomText")
    @Expose
    val bottomText: String,
    @SerializedName("dateCreated")
    @Expose
    val dateCreated: String,
    @SerializedName("topText")
    @Expose
    val topText: String
)
