package com.jalexy.meme.main.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MemeContainerDto (
    @SerializedName("MemeInfo")
    @Expose
    val mee: MemeDto? = null
)
