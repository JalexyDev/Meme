package com.jalexy.meme.main.data.api

import com.jalexy.meme.main.data.models.MemberInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MemeApiService {

    @GET("memes/{$ID}")
    suspend fun getMeme(@Path(ID) id: Int): MemberInfoResponse

    companion object {
        private const val ID = "id"
    }
}