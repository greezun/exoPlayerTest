package com.greezun.exoplayertest.data.repositoryimpl

import com.greezun.exoplayertest.data.local.dao.VideoDao
import com.greezun.exoplayertest.data.local.entity.VideoEntity
import com.greezun.exoplayertest.data.model.Video
import com.greezun.exoplayertest.domain.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoDao: VideoDao
) : VideoRepository {
    override suspend fun saveVideo(video: Video) {
        videoDao.insert(
            VideoEntity(
                id = video.id,
                width = video.width,
                height = video.height,
                duration = video.duration,
                image = video.image,
                link = video.videoFiles[0].link,
                userName = video.user.name
            )
        )
    }

    override suspend fun fetchVideo(): Flow<List<VideoEntity>> {
        return videoDao.getAllVideos()

    }

    override suspend fun isEmpty(): Boolean {
        return videoDao.isVideoListEmpty()
    }
}