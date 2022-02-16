package com.jalexy.meme.main.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllMemesResponse(
    @SerializedName("code")
    @Expose
    val code: String,
    @SerializedName("data")
    @Expose
    val data: List<MemeDto>,
    @SerializedName("message")
    @Expose
    val message: String,
    @SerializedName("next")
    @Expose
    val next: String

)