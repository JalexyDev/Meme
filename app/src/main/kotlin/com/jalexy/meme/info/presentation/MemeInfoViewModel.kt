package com.jalexy.meme.info.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalexy.meme.info.domain.usecases.GetFavoriteMemeInfoUseCase
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.MemeInfo
import com.jalexy.meme.main.domain.models.ScreenState
import com.jalexy.meme.main.domain.usecases.ContainsPrimaryKeyMemeInfoUseCase
import com.jalexy.meme.main.domain.usecases.LoadMemeInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MemeInfoViewModel @Inject constructor(
    private val loadMemeInfoUseCase: LoadMemeInfoUseCase,
    private val getFavoriteMemeUseCase: GetFavoriteMemeInfoUseCase,
    private val containsPrimaryKeyMemeInfoUseCase: ContainsPrimaryKeyMemeInfoUseCase,
) : ViewModel() {

    var isFavoriteMemeInfo = false

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _memeInfo = MutableLiveData<MemeInfo>()
    val memeInfo: LiveData<MemeInfo> = _memeInfo

    fun loadMemeInfo(meme: Meme) {
        viewModelScope.launch {
            val dbCheck = withContext(Dispatchers.IO) {
                dbCheckItem(meme)
            }
            if (dbCheck) {
                loadMemeDB(meme.id)
            } else {
                loadMeme(meme.id)
            }
        }
    }

    private fun loadMeme(id: Int) {
        viewModelScope.launch {
                _screenState.value = ScreenState.Loading
                try {
                    withContext(Dispatchers.IO) {
                    val meme = loadMemeInfoUseCase(id)
                        _memeInfo.postValue(meme)
                    }
                    _screenState.value = ScreenState.Content
                } catch (e: Exception) {
                    e.printStackTrace()
                    _screenState.value = ScreenState.Error("Нет соединения")
            }
        }
    }

    private suspend fun loadMemeDB(id: Int) {
        try {
            withContext(Dispatchers.IO) {
                val meme = getFavoriteMemeUseCase(id)
                _memeInfo.postValue(meme)
            }
            _screenState.value = ScreenState.Content
        } catch (e: Exception) {
            e.printStackTrace()
            _screenState.value = ScreenState.Error("Нет соединения")
        }
    }

    private suspend fun dbCheckItem(meme: Meme): Boolean = coroutineScope {
        withContext(Dispatchers.IO) {
            try {
                isFavoriteMemeInfo = containsPrimaryKeyMemeInfoUseCase(meme.id)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return@withContext isFavoriteMemeInfo
        }
    }
}