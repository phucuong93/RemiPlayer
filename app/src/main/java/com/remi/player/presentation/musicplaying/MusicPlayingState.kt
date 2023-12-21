package com.remi.player.presentation.musicplaying

import com.remi.player.data.local.model.Audio

data class MusicPlayingState(
    var isLoading: Boolean = false,
    var searchText: String = "",
    var listAudio: List<Audio> = emptyList()
)