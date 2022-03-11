package com.jalexy.meme.main.data.database

import androidx.room.*

@Dao
interface MemeInfoDao {

    @Query("SELECT * FROM info_items WHERE id=:memeId LIMIT 1")
    fun getMemeInfo(memeId: Int): MemeInfoDBModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemeInfo(memeInfo: MemeInfoDBModel)

    @Delete
    fun removeMemeInfo(memeInfo: MemeInfoDBModel)

    @Query("SELECT count(*)!=0 FROM info_items WHERE id=:memeId ")
    fun containsPrimaryKeyMemeInfo(memeId: Int): Boolean
}