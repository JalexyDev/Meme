package com.jalexy.meme.dashboard.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalexy.meme.dashboard.domain.usecases.LoadMemeAllUseCase
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val loadMemeAllUseCase: LoadMemeAllUseCase,
) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _meme = MutableLiveData<List<Meme>>()
    val meme: LiveData<List<Meme>> = _meme


    fun loadAllMeme(page: Int) {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            try {
                val meme = loadMemeAllUseCase(page)
                _meme.postValue(meme)
                _screenState.value = ScreenState.Content
            } catch (e: Exception) {
                e.printStackTrace()
                //todo Заменить на нормальное сообщение =)
                _screenState.value = ScreenState.Error("Response sucked")
            }
        }
    }

//    fun loadMeme(id: Int) {
//        viewModelScope.launch {
//            _screenState.value = ScreenState.Loading
//            try{
//                val meme = loadMemeInfoUseCase(id) //заменить
//                _memeInfo.postValue(meme)          //заменить
//                _screenState.value = ScreenState.Content  //оставить
//            } catch (e: Exception) {
//                e.printStackTrace()
//                //todo Заменить на нормальное сообщение =)
//                _screenState.value = ScreenState.Error("Response sucked")
//            }
//        }
//    }
}