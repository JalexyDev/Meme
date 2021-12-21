package com.jalexy.meme.info.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalexy.meme.info.domain.usecases.LoadMemeInfoUseCase
import com.jalexy.meme.main.domain.MemeRepository
import com.jalexy.meme.main.domain.models.MemeInfo
import com.jalexy.meme.main.domain.models.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MemeInfoViewModel @Inject constructor(
    private val loadMemeInfoUseCase: LoadMemeInfoUseCase
) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _memeInfo = MutableLiveData<MemeInfo>()
    val memeInfo: LiveData<MemeInfo> = _memeInfo

    fun loadMeme(id: Int) {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            try{
                val meme = loadMemeInfoUseCase(id)
                _memeInfo.postValue(meme)
                _screenState.value = ScreenState.Content
            } catch (e: Exception) {
                e.printStackTrace()
                //todo Заменить на нормальное сообщение =)
                _screenState.value = ScreenState.Error("Response sucked")
            }
        }
    }
}