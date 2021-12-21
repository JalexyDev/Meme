package com.jalexy.meme.main.data.mappers

import com.jalexy.meme.main.data.models.MemeInfoDto
import com.jalexy.meme.main.data.models.MemeSubmissionDto
import com.jalexy.meme.main.domain.models.MemeInfo
import com.jalexy.meme.main.domain.models.MemeSubmission

class MemeMapper {

    fun mapMemeDto(dtoItem: MemeInfoDto) = with(dtoItem) {
        MemeInfo(
            id = id,
            bottomText = bottomText,
            details = details,
            imageUrl = imageUrl,
            name = name,
            submissions = submissions.map(::mapMemeSubmissionDto),
            tags = tags,
            thumb = thumb,
            topText = topText
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