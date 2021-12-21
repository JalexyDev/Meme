package com.jalexy.meme.main.data.api

import com.jalexy.meme.main.data.mappers.MemeMapper
import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.MemeInfo

class MemeRepositoryImpl(
    private val apiService: MemeApiService,
    private val mapper: MemeMapper
): MemeRepository {

    override suspend fun getMeme(id: Int): MemeInfo {
        val meme = apiService.getMeme(id)
        return mapper.mapMemeDto(meme)
    }
}