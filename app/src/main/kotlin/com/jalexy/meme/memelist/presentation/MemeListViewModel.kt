package com.jalexy.meme.memelist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalexy.meme.memelist.domain.usecases.*
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.ScreenState
import com.jalexy.meme.main.domain.usecases.ContainsPrimaryKeyMemeInfoUseCase
import com.jalexy.meme.main.domain.usecases.LoadMemeInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MemeListViewModel @Inject constructor(
    private val loadMemeAllUseCase: LoadMemeAllUseCase,
    private val loadMemeInfoUseCase: LoadMemeInfoUseCase,
    private val addFavoriteMemeUseCase: AddFavoriteMemeUseCase,
    private val addFavoriteMemeInfoUseCase: AddFavoriteMemeInfoUseCase,
    private val removeFromFavoriteUseCase: RemoveFromFavoriteUseCase,
    private val removeMemeInfoFromFavoriteUseCase: RemoveMemeInfoFromFavoriteUseCase,
    private val containsPrimaryKeyUseCase: ContainsPrimaryKeyUseCase,
    private val containsPrimaryKeyMemeInfoUseCase: ContainsPrimaryKeyMemeInfoUseCase,
    private val getDemoMemesUseCase: GetDemoMemesUseCase
) : ViewModel() {

    private val listMeme: MutableList<Meme> by lazy { mutableListOf() }
    private var countPage = 1
    private var isFinished = false
    private var isLoading = false

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _meme = MutableLiveData<List<Meme>>()
    val meme: LiveData<List<Meme>> = _meme

    init {
        loadAllMeme()
    }

    fun loadAllMeme() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            try {
                withContext(Dispatchers.IO) {
                    val meme = loadMemeAllUseCase(countPage)
                    if (meme.isNullOrEmpty()) {
                        isFinished = true
                    } else {
                        val memeCheck = meme.map { dbCheckItem(it) }
                        listMeme.addAll(memeCheck)
                    }
                }
                _meme.postValue(listMeme)
                _screenState.value = ScreenState.Content
            } catch (e: Exception) {
                e.printStackTrace()
                _screenState.value = ScreenState.Error("Нет соединения")
            } finally {
                isLoading = false
            }
        }
    }

    fun launchDemoData() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            withContext(Dispatchers.IO) {
                val memeDemo = getDemoMemesUseCase()
                if (memeDemo.isNullOrEmpty()) {
                    isFinished = true
                } else {
                    val memeCheck = memeDemo.map { dbCheckItem(it) }
                    listMeme.addAll(memeCheck)
                }
            }
            _meme.postValue(listMeme)
            _screenState.value = ScreenState.Content
        }
    }

    fun addMemeItemToDB(meme: Meme, callback: (item: Meme) -> Unit): Meme {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val dbItem = dbCheckItem(meme)
                    if (!dbItem.isFavorite) {
                        addFavoriteMemeUseCase(meme)
                        meme.isFavorite = true
                    } else {
                        removeFromFavoriteUseCase(meme)
                        meme.isFavorite = false
                    }
                    callback(meme)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _screenState.postValue(ScreenState.Error("Не удалось добавить в базу данных"))
            }
            addAndRemoveMemeInfoToDB(meme)
        }
        return meme
    }

    private suspend fun addAndRemoveMemeInfoToDB(meme: Meme) {
        withContext(Dispatchers.IO) {
            val isFavoriteMemeInfo = dbCheckItemMemeInfo(meme)
            try {
                val memeInfo = loadMemeInfoUseCase(meme.id)
                if (isFavoriteMemeInfo) {
                    removeMemeInfoFromFavoriteUseCase(memeInfo)
                } else {
                    addFavoriteMemeInfoUseCase(memeInfo)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadingNextPage() {
        if (isFinished || isLoading) {
            return
        }
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    isLoading = true
                    countPage++
                    loadAllMeme()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun dbCheckItem(meme: Meme): Meme {
        try {
            withContext(Dispatchers.IO) {
                meme.isFavorite = containsPrimaryKeyUseCase(meme.id)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return meme
    }

    private suspend fun dbCheckItemMemeInfo(meme: Meme): Boolean {
        var isFavoriteMemeInfo = false
        try {
            withContext(Dispatchers.IO) {
                isFavoriteMemeInfo = containsPrimaryKeyMemeInfoUseCase(meme.id)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return isFavoriteMemeInfo
    }
}
