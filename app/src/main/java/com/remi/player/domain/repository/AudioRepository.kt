package com.remi.player.domain.repository

import com.remi.player.data.local.model.Audio
import com.remi.player.utils.Resource
import kotlinx.coroutines.flow.Flow


fun interface AudioRepository {

    fun getAudioData(): Flow<Resource<List<Audio>>>
}