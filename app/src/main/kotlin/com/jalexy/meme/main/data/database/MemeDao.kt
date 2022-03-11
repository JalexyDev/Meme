package com.jalexy.meme.main.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemeDao {

    @Query("SELECT * FROM meme_items")
    fun getMeme(): LiveData<List<MemeDbModel>>

    @Query("SELECT * FROM meme_items WHERE id=:memeId LIMIT 1")
    fun getMemeItem(memeId: Int): MemeDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeme(meme: MemeDbModel)

    @Delete
    fun removeMeme(meme: MemeDbModel)

    @Query("SELECT count(*)!=0 FROM meme_items WHERE id=:memeInfoId ")
    fun containsPrimaryKey(memeInfoId: Int): Boolean
}