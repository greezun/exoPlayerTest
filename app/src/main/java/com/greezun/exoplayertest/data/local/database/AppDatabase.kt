package com.greezun.exoplayertest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greezun.exoplayertest.data.local.dao.VideoDao
import com.greezun.exoplayertest.data.local.entity.VideoEntity

@Database(entities = [ VideoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {


    abstract fun videoDao(): VideoDao
}
