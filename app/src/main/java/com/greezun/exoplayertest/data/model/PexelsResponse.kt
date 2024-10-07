package com.greezun.exoplayertest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    val media: List<Video>
)

@Serializable
data class Video(
    val id: Long,
    val width: Int,
    val height: Int,
    val duration: Int,
    val image: String,
    val user: User,
    @SerialName("video_files")
    val videoFiles: List<VideoFile>
)

@Serializable
data class User(
    val name: String
)

@Serializable
data class VideoFile(
    val id: Long,
    val quality: String,
    val link: String,
)