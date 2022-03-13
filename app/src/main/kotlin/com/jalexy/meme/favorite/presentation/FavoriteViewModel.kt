package com.jalexy.meme.favorite.presentation

import androidx.lifecycle.*
import com.jalexy.meme.favorite.domain.usecases.GetFavoriteMemeListUseCase
import com.jalexy.meme.memelist.domain.usecases.RemoveFromFavoriteUseCase
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteMemeListUseCase: GetFavoriteMemeListUseCase,
    private val removeFromFavoriteUseCase: RemoveFromFavoriteUseCase,
) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _meme = MutableLiveData<List<Meme>>()
    val meme: LiveData<List<Meme>> = _meme

    val getFavoriteList by lazy{
        getFavoriteMemeListUseCase.invoke()
    }

    init {
        getAllFavoriteMemes()
    }

    private fun getAllFavoriteMemes() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            try {
                _screenState.value = ScreenState.Content
            } catch (e: Exception) {
                e.printStackTrace()
                _screenState.value = ScreenState.Error("Нет соединения")
            }
        }
    }

    fun deleteMemeItemToDB(meme: Meme) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    removeFromFavoriteUseCase(meme)
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                _screenState.value = ScreenState.Error("Не удалось удалить из базы данных")
            }
        }
    }
}