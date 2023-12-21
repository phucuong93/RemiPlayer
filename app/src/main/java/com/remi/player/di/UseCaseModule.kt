package com.remi.player.di

import com.remi.player.domain.repository.AudioRepository
import com.remi.player.domain.usecase.media.AudioUseCase
import com.remi.player.domain.usecase.media.GetAudio
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideAudioUseCase(repository: AudioRepository): AudioUseCase {
        return AudioUseCase(getAudio = GetAudio(repository))
    }
}
