package com.greezun.exoplayertest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greezun.exoplayertest.data.local.dao.VideoDao
import com.greezun.exoplayertest.data.local.dao.VideoFileDao
import com.greezun.exoplayertest.data.local.entity.VideoEntity
import com.greezun.exoplayertest.data.local.entity.VideoFileEntity

@Database(entities = [VideoFileEntity::class, VideoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videoFileDao(): VideoFileDao
    abstract fun videoDao(): VideoDao
}
