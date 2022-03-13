package com.jalexy.meme.memelist.domain.usecases

import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class LoadMemeAllUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

    suspend operator fun invoke(page: Int): List<Meme> = memeRepository.getAllMemes(page)
}