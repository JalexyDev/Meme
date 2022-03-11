package com.jalexy.meme.dashboard.domain.usecases

import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class RemoveFromFavoriteUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

    suspend operator fun invoke(meme: Meme) = memeRepository.removeMemeFromFavorite(meme)
}