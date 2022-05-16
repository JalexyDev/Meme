package com.jalexy.meme.main.domain

import androidx.lifecycle.LiveData
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.MemeInfo

interface MemeRepository {

    suspend fun getMeme(id: Int): MemeInfo

    suspend fun getAllMemes(page: Int): List<Meme>

    suspend fun getDemoMemes(): List<Meme>

    suspend fun getFavoriteMeme(memeId: Int): Meme

    suspend fun addFavoriteMeme(meme: Meme)

    suspend fun removeMemeFromFavorite(meme: Meme)

    fun containsPrimaryKey(memeId: Int): Boolean

    fun getFavoriteMemeList(): LiveData<List<Meme>>

//    MemeInfo
    suspend fun addFavoriteMemeInfo(memeInfo: MemeInfo)

    fun getFavoriteMemeInfo(memeId: Int): MemeInfo

    suspend fun removeMemeInfoFromFavorite(memeInfo: MemeInfo)

    fun containsPrimaryKeyMemeInfo(memeId: Int): Boolean
}