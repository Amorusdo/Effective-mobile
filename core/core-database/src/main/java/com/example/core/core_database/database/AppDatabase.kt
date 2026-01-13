package com.example.core.core_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.core_database.dao.FavoriteCourseDao
import com.example.core.core_database.entity.FavoriteCourseEntity

@Database(
    entities = [FavoriteCourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteCourseDao(): FavoriteCourseDao

    companion object {
        const val DATABASE_NAME = "courses_database"
    }
}