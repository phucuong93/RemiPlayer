package com.remi.player.presentation.mymusic

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.remi.player.domain.usecase.media.AudioUseCase
import com.remi.player.presentation.BaseViewModel
import com.remi.player.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMusicModel @Inject constructor(private val audioUseCase: AudioUseCase) : BaseViewModel() {

    init {
        viewModelScope.launch {
            delay(1000L)
            onRequestAllPermission {
                getAndFetchAudio()
            }
        }

    }

    private val _state = mutableStateOf(MyMusicState())
    val state: State<MyMusicState> = _state

    fun setSearchText(searchText: String) {
        _state.value = _state.value.copy(searchText = searchText)
    }

    private fun getAndFetchAudio() {
        viewModelScope.launch {
            audioUseCase.getAudio().collect {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { listSong ->
                            _state.value = _state.value.copy(listAudio = listSong)
                        }
                    }

                    is Resource.Error -> {
                        showSnackBar(it.uiText!!)
                    }

                    else -> Unit
                }
            }
        }

    }
}