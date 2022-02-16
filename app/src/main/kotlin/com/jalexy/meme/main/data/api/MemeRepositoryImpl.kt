package com.jalexy.meme.main.data.api

import com.jalexy.meme.main.data.mappers.MemeMapper
import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.MemeInfo
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    private val apiService: MemeApiService,
    private val mapper: MemeMapper
): MemeRepository {

    override suspend fun getMeme(id: Int): MemeInfo {
        val meme = apiService.getMeme(id).data
        return mapper.mapMemeInfoDto(meme)
    }

    override suspend fun getAllMemes(page: Int): List<Meme> {
        val memes = apiService.getAllMeme(page).data
        return mapper.mapAllMemesDto(memes)
    }
}