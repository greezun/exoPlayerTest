package com.greezun.exoplayertest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_files")
data class VideoFileEntity(
    @PrimaryKey
    val id: Long,
    val quality: String,
    val link: String,
    val videoId: Long
)