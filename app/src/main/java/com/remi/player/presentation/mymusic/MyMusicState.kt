package com.remi.player.presentation.mymusic

import com.remi.player.data.local.model.Audio

data class MyMusicState(
    var isLoading: Boolean = false,
    var searchText: String = "",
    var listAudio: List<Audio> = emptyList()
)