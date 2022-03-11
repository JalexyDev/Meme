package com.jalexy.meme.main.data.api

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jalexy.meme.main.data.database.AppDataBase
import com.jalexy.meme.main.data.mappers.MemeMapper
import com.jalexy.meme.main.data.mappers.MemeMapperRoom
import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.MemeInfo
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    application: Application,
    private val apiService: MemeApiService,
    private val mapper: MemeMapper,
    private val mapperRoom: MemeMapperRoom,
) : MemeRepository {

    private val memeDao = AppDataBase.getInstance(application).memeDao()
    private val memeInfoDao = AppDataBase.getInstance(application).memeInfoDao()

    override suspend fun getMeme(id: Int): MemeInfo {
        val meme = apiService.getMeme(id).data
        return mapper.mapMemeInfoDto(meme)
    }

    override suspend fun getAllMemes(page: Int): List<Meme> {
        val memes = apiService.getAllMeme(page).data
        return mapper.mapAllMemesDto(memes)
    }

    override suspend fun getFavoriteMeme(memeId: Int): Meme {
        return mapperRoom.mapDbModelToEntity(memeDao.getMemeItem(memeId))
    }

    override fun getFavoriteMemeList(): LiveData<List<Meme>> {
        return Transformations.map(memeDao.getMeme()) {
            it.map {
                mapperRoom.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun addFavoriteMeme(meme: Meme) {
        memeDao.insertMeme(mapperRoom.mapDtoToDbModel(meme))
    }

    override suspend fun removeMemeFromFavorite(meme: Meme) {
        memeDao.removeMeme(mapperRoom.mapDtoToDbModel(meme))
    }

    override fun containsPrimaryKey(memeId: Int): Boolean {
        return memeDao.containsPrimaryKey(memeId)
    }

    //MemeInfo
    override suspend fun addFavoriteMemeInfo(memeInfo: MemeInfo) {
        memeInfoDao.insertMemeInfo(mapperRoom.mapMemeInfoModelInfo(memeInfo))
    }

    override fun getFavoriteMemeInfo(memeId: Int): MemeInfo {
        return mapperRoom.mapDbModelInfoToEntity(memeInfoDao.getMemeInfo(memeId))
    }

    override suspend fun removeMemeInfoFromFavorite(memeInfo: MemeInfo) {
        memeInfoDao.removeMemeInfo(mapperRoom.mapMemeInfoModelInfo(memeInfo))
    }

    override fun containsPrimaryKeyMemeInfo(memeId: Int): Boolean {
        return memeInfoDao.containsPrimaryKeyMemeInfo(memeId)
    }
}