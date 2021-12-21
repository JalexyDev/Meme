package com.jalexy.meme.main.domain.models

sealed class ScreenState {

    object Loading: ScreenState()

    object Content: ScreenState()

    data class Error(val text: String) : ScreenState()
}
