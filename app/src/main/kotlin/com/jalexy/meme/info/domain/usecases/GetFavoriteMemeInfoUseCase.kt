package com.jalexy.meme.info.domain.usecases

import com.jalexy.meme.main.domain.MemeRepository
import javax.inject.Inject

class GetFavoriteMemeInfoUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

    operator fun invoke(id: Int) = memeRepository.getFavoriteMemeInfo(id)
}