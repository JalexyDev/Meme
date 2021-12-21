package com.jalexy.meme.main.data.mappers

import com.jalexy.meme.main.data.models.MemeInfoDto
import com.jalexy.meme.main.data.models.MemeSubmissionDto
import com.jalexy.meme.main.domain.models.MemeInfo
import com.jalexy.meme.main.domain.models.MemeSubmission
import javax.inject.Inject

class MemeMapper @Inject constructor() {

    fun mapMemeDto(dtoItem: MemeInfoDto) = with(dtoItem) {
        MemeInfo(
            id = id,
            bottomText = bottomText.orEmpty(),
            details = details.orEmpty(),
            imageUrl = imageUrl.orEmpty(),
            name = name.orEmpty(),
            submissions = submissions?.map(::mapMemeSubmissionDto).orEmpty(),
            tags = tags.orEmpty(),
            thumb = thumb.orEmpty(),
            topText = topText.orEmpty()
        )
    }

    private fun mapMemeSubmissionDto(dtoItem: MemeSubmissionDto) = with(dtoItem) {
        MemeSubmission(
            bottomText = bottomText,
            dateCreated = dateCreated,
            topText = topText
        )
    }
}