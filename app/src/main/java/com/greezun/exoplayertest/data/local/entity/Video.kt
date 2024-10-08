package com.greezun.exoplayertest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey
    val id: Long,
    val width: Int,
    val height: Int,
    val duration: Int,
    val image: String,
    val link: String,
    val userName: String
)