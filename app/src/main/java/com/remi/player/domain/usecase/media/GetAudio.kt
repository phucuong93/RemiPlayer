package com.remi.player.domain.usecase.media

import com.remi.player.data.local.model.Audio
import com.remi.player.domain.repository.AudioRepository
import com.remi.player.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAudio(private val repository: AudioRepository) {
    operator fun invoke(): Flow<Resource<List<Audio>>> = flow {
        repository.getAudioData().collect {
            emit(it)
        }
    }
}
