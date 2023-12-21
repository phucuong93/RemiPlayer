package com.remi.player.data.repository

import com.remi.player.data.local.ContentResolverHelper
import com.remi.player.data.local.model.Audio
import com.remi.player.domain.repository.AudioRepository
import com.remi.player.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CAudioRepository @Inject constructor(
    private val contentResolverHelper: ContentResolverHelper
) : AudioRepository {
    override fun getAudioData(): Flow<Resource<List<Audio>>> {
        return contentResolverHelper.getAudioData()
    }
}