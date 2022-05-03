package com.jalexy.meme.main.domain.models

data class MemeInfoDemo(
    val id: Int,
    val bottomText: String = "Milos",
    val details: String = "Ricardo Milos , is a superhero who is mostly known for his dancing video, which became one of the most famous memes of 2019 and still to this day.",
    val imageUrl: String = "demoImage",
    val name: String = "Ricardo Milos",
    val submissions: List<MemeSubmission> = emptyList(),
    val tags: String = "Ricardo Milos , is a superhero who is mostly known for his dancing video, which became one of the most famous memes of 2019 and still to this day.",
    val thumb: String? = null,
    val topText: String = "Ricardo"
)
