package com.greezun.exoplayertest.domain

import com.greezun.exoplayertest.data.local.entity.VideoEntity
import com.greezun.exoplayertest.data.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun saveVideo(video: Video)
    suspend fun fetchVideo(): Flow<List<VideoEntity>>
    suspend fun isEmpty():Boolean
}