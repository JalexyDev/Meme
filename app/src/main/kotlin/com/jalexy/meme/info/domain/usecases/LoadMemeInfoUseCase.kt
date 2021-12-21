package com.jalexy.meme.info.domain.usecases

import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.MemeInfo
import javax.inject.Inject

class LoadMemeInfoUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

    suspend operator fun invoke(id: Int): MemeInfo = memeRepository.getMeme(id)
}