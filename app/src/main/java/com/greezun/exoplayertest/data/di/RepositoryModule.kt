package com.greezun.exoplayertest.data.di

import android.content.Context
import com.greezun.exoplayertest.data.local.dao.VideoDao
import com.greezun.exoplayertest.data.repositoryimpl.VideoRepositoryImpl
import com.greezun.exoplayertest.data.tools.NetworkListener
import com.greezun.exoplayertest.domain.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideVideoRepository(
        videoDao: VideoDao
    ): VideoRepository = VideoRepositoryImpl(videoDao)

    @Singleton
    @Provides
    fun provideNetListener(@ApplicationContext context: Context) = NetworkListener(context)

}