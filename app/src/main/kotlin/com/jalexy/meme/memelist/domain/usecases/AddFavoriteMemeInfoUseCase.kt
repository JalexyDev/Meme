package com.jalexy.meme.memelist.domain.usecases

import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.MemeInfo
import javax.inject.Inject

class AddFavoriteMemeInfoUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

    suspend operator fun invoke(memeInfo: MemeInfo) = memeRepository.addFavoriteMemeInfo(memeInfo)
}