package com.jalexy.meme.main.data.api

import com.jalexy.meme.main.data.models.AllMemesResponse
import com.jalexy.meme.main.data.models.MemberInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MemeApiService {

    @GET("memes/{$ID}")
    suspend fun getMeme(@Path(ID) id: Int): MemberInfoResponse

    @GET("{$PAGE}")
    suspend fun getAllMeme(@Path(PAGE) page: Int): AllMemesResponse

//    @GET(PAGE)
//    suspend fun getAllMeme(
//        @Query(PAGE) page: Int,
//    ): AllMemesResponse

    companion object {
        private const val ID = "id"
        private const val PAGE = "page"
    }


}