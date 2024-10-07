package com.greezun.exoplayertest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greezun.exoplayertest.data.local.entity.VideoFileEntity

@Dao
interface VideoFileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videoFile: VideoFileEntity)

    @Query("SELECT * FROM video_files WHERE videoId = :videoId")
    suspend fun getVideoFilesByVideoId(videoId: Long): List<VideoFileEntity>
}