package com.jalexy.meme.main.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MemeListDto (
    @SerializedName("Data")
    @Expose
    val names: List<MemeContainerDto>? = null
)
