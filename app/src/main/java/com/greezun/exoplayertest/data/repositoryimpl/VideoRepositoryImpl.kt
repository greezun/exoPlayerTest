package com.greezun.exoplayertest.data.repositoryimpl

import com.greezun.exoplayertest.data.local.dao.VideoDao
import com.greezun.exoplayertest.data.local.dao.VideoFileDao
import com.greezun.exoplayertest.data.local.entity.VideoEntity
import com.greezun.exoplayertest.data.local.entity.VideoFileEntity
import com.greezun.exoplayertest.data.model.User
import com.greezun.exoplayertest.data.model.Video
import com.greezun.exoplayertest.data.model.VideoFile
import com.greezun.exoplayertest.domain.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoDao: VideoDao,
    private val videoFileDao: VideoFileDao
) : VideoRepository {
    override suspend fun saveVideo(video: Video) {
        videoDao.insert(
            VideoEntity(
                id = video.id,
                width = video.width,
                height = video.height,
                duration = video.duration,
                image = video.image,
                userName = video.user.name
            )
        )

        video.videoFiles.forEach {
            videoFileDao.insert(
                VideoFileEntity(
                    id = it.id,
                    quality = it.quality,
                    link = it.link,
                    videoId = video.id
                )
            )
        }
    }

    override suspend fun fetchVideo(): Flow<List<Video>> {
        return videoDao.getAllVideos().flatMapLatest { videoEntities ->
            flow {
                val videos = videoEntities.map { videoEntity ->
                    val videoFiles = videoFileDao.getVideoFilesByVideoId(videoEntity.id)
                    Video(
                        id = videoEntity.id,
                        width = videoEntity.width,
                        height = videoEntity.height,
                        duration = videoEntity.duration,
                        image = videoEntity.image,
                        user = User(name = videoEntity.userName),
                        videoFiles = videoFiles.map { videoFileEntity ->
                            VideoFile(
                                id = videoFileEntity.id,
                                quality = videoFileEntity.quality,
                                link = videoFileEntity.link
                            )
                        }
                    )
                }
                emit(videos)
            }
        }
    }

    override suspend fun isEmpty(): Boolean {
        return videoDao.isVideoListEmpty()
    }
}