package com.jalexy.meme.main.domain.usecases

import com.jalexy.meme.main.domain.MemeRepository
import javax.inject.Inject

class ContainsPrimaryKeyMemeInfoUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

 operator fun invoke(memeId: Int) = memeRepository.containsPrimaryKeyMemeInfo(memeId)
}