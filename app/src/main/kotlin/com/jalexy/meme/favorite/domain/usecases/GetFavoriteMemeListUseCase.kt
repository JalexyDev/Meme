package com.jalexy.meme.favorite.domain.usecases

import androidx.lifecycle.LiveData
import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class GetFavoriteMemeListUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

    operator fun invoke() = memeRepository.getFavoriteMemeList()
}