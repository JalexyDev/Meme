package com.jalexy.meme.main.domain

import com.jalexy.meme.main.domain.models.MemeInfo

interface MemeRepository {

    suspend fun getMeme(id: Int): MemeInfo
}