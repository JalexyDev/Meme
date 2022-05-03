package com.jalexy.meme.memelist.domain.usecases

import com.jalexy.meme.main.domain.MemeRepository
import javax.inject.Inject

class GetDemoMemesUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

    suspend operator fun invoke() = memeRepository.getDemoMemes()
}