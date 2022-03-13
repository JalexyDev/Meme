package com.jalexy.meme.memelist.domain.usecases

import com.jalexy.meme.main.domain.MemeRepository
import javax.inject.Inject

class ContainsPrimaryKeyUseCase @Inject constructor(
    private var memeRepository: MemeRepository
) {

 operator fun invoke(memeId: Int) = memeRepository.containsPrimaryKey(memeId)
}