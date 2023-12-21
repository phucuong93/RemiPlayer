package com.remi.player.presentation.musicplaying

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.remi.player.domain.usecase.media.AudioUseCase
import com.remi.player.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicPlayingModel @Inject constructor(private val audioUseCase: AudioUseCase) : BaseViewModel() {


    private val _state = mutableStateOf(MusicPlayingState())
    val state: State<MusicPlayingState> = _state

    fun setSearchText(searchText: String) {
        _state.value = _state.value.copy(searchText = searchText)
    }

}