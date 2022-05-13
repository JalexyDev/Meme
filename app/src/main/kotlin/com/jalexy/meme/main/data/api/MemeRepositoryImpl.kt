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
                "http://images.shoutwiki.com/ytp/9/97/%D0%A0%D0%B8%D0%BA%D0%B0%D1%80%D0%B4%D0%BE_%D0%9C%D0%B8%D0%BB%D0%BE%D1%81.jpg",
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
            MemeInfo(
                memeId,
                "Milos",
                "Ricardo Milos , is a superhero who is mostly known for his dancing video, which became one of the most famous memes of 2019 and still to this day.",
                "http://images.shoutwiki.com/ytp/9/97/%D0%A0%D0%B8%D0%BA%D0%B0%D1%80%D0%B4%D0%BE_%D0%9C%D0%B8%D0%BB%D0%BE%D1%81.jpg",
                "Ricardo Milos",
                emptyList(),
                "Ricardo Milos , is a superhero who is mostly known for his dancing video, which became one of the most famous memes of 2019 and still to this day.",
                null,
                "Ricardo"
            )
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