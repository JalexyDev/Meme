package com.jalexy.meme.main.data.api

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jalexy.meme.R
import com.jalexy.meme.main.data.database.AppDataBase
import com.jalexy.meme.main.data.mappers.MemeMapper
import com.jalexy.meme.main.data.mappers.MemeMapperRoom
import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.MemeInfo
import com.jalexy.meme.main.domain.models.MemeInfoDemo
import javax.inject.Inject
import kotlin.random.Random

class MemeRepositoryImpl @Inject constructor(
    application: Application,
    private val apiService: MemeApiService,
    private val mapper: MemeMapper,
    private val mapperRoom: MemeMapperRoom
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

    override suspend fun getDemoMemes(): List<Meme> {
        val listMemeDemo: MutableList<Meme> by lazy { mutableListOf() }
        for (i in 0 until 10) {
            val item = Meme(
                false,
                i - 100,
                "Milos",
                "demoImage",
                "Ricardo Milos",
                "Ricardo Milos , is a superhero who is mostly known for his dancing video, which became one of the most famous memes of 2019 and still to this day.",
                "Ricardo"
            )
            listMemeDemo.add(item)
        }
        return listMemeDemo
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
        return if (memeId < 0) {
            mapper.memeInfoDemoToMemeInfo(MemeInfoDemo(memeId))
        } else {
            mapperRoom.mapDbModelInfoToEntity(memeInfoDao.getMemeInfo(memeId))
        }
    }

    override suspend fun removeMemeInfoFromFavorite(memeInfo: MemeInfo) {
        memeInfoDao.removeMemeInfo(mapperRoom.mapMemeInfoModelInfo(memeInfo))
    }

    override fun containsPrimaryKeyMemeInfo(memeId: Int): Boolean {
        return if (memeId < 0) {
            true
        } else {
            memeInfoDao.containsPrimaryKeyMemeInfo(memeId)
        }
    }
}