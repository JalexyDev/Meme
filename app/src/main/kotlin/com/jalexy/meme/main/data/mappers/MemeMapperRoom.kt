package com.jalexy.meme.main.data.mappers

import com.jalexy.meme.main.data.database.MemeDbModel
import com.jalexy.meme.main.data.database.MemeInfoDBModel
import com.jalexy.meme.main.data.models.MemeDto
import com.jalexy.meme.main.data.models.MemeInfoDto
import com.jalexy.meme.main.data.models.MemeSubmissionDto
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.MemeInfo
import com.jalexy.meme.main.domain.models.MemeSubmission
import javax.inject.Inject

class MemeMapperRoom @Inject constructor() {

    fun mapDtoToDbModel(meme: Meme) = MemeDbModel(
        id = meme.id,
        bottomText = meme.bottomText.orEmpty(),
        image = meme.image.orEmpty(),
        name = meme.name.orEmpty(),
        tags = meme.tags.orEmpty(),
        topText = meme.topText.orEmpty(),
        isFavorite = meme.isFavorite
    )

    fun mapMemeToMemeDto(meme: Meme) = MemeDto(
        id = meme.id,
        bottomText = meme.bottomText.orEmpty(),
        image = meme.image.orEmpty(),
        name = meme.name.orEmpty(),
        tags = meme.tags.orEmpty(),
        topText = meme.topText.orEmpty(),
        isFavorite = meme.isFavorite
    )

    fun mapDbModelToEntity(dbModel: MemeDbModel) = Meme(
        id = dbModel.id,
        bottomText = dbModel.bottomText.orEmpty(),
        image = dbModel.image.orEmpty(),
        name = dbModel.name.orEmpty(),
        tags = dbModel.tags.orEmpty(),
        topText = dbModel.topText.orEmpty(),
        isFavorite = dbModel.isFavorite
    )

    fun mapMemeInfoModelInfo(memeInfo: MemeInfo) = MemeInfoDBModel(
        id = memeInfo.id,
        bottomText = memeInfo.bottomText.orEmpty(),
        details = memeInfo.details.orEmpty(),
        imageUrl = memeInfo.imageUrl.orEmpty(),
        name = memeInfo.name.orEmpty(),
        submissions = memeInfo.submissions,
        tags = memeInfo.tags.orEmpty(),
        thumb = memeInfo.thumb,
        topText = memeInfo.topText.orEmpty()
    )

    fun mapMemeInfoToMemeDto(memeInfo: MemeInfo) = MemeInfoDto(
        id = memeInfo.id,
        bottomText = memeInfo.bottomText,
        details = memeInfo.details,
        imageUrl = memeInfo.imageUrl,
        name = memeInfo.name,
        submissions = memeInfo.submissions.map(::mapMemeSubmissionInfo),
        tags = memeInfo.tags,
        thumb = memeInfo.thumb,
        topText = memeInfo.topText
    )

    fun mapDbModelInfoToEntity(dbModelInfo: MemeInfoDBModel) = MemeInfo(
        id = dbModelInfo.id,
        bottomText = dbModelInfo.bottomText.orEmpty(),
        details = dbModelInfo.details.orEmpty(),
        imageUrl = dbModelInfo.imageUrl.orEmpty(),
        name = dbModelInfo.name.orEmpty(),
        submissions = dbModelInfo.submissions.map(::mapMemeSubmissionEntity),
        tags = dbModelInfo.tags.orEmpty(),
        thumb = dbModelInfo.thumb,
        topText = dbModelInfo.topText.orEmpty()
    )

    private fun mapMemeSubmissionDto(dtoItem: MemeSubmissionDto) = with(dtoItem) {
        MemeSubmission(
            bottomText = bottomText,
            dateCreated = dateCreated,
            topText = topText
        )
    }

    private fun mapMemeSubmissionInfo(dtoItem: MemeSubmission): MemeSubmissionDto = with(dtoItem) {
        MemeSubmissionDto(
            bottomText = bottomText,
            dateCreated = dateCreated,
            topText = topText
        )
    }


    private fun mapMemeSubmissionEntity(dtoItem: MemeSubmission): MemeSubmission {
        return with(dtoItem) {
            MemeSubmission(
                bottomText = bottomText,
                dateCreated = dateCreated,
                topText = topText
            )
        }
    }
}
