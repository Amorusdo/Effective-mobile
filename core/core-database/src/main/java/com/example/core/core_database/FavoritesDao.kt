package com.example.core.core_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorite_courses")
    suspend fun getAll(): List<FavoriteCourseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: FavoriteCourseEntity)

    @Delete
    suspend fun delete(course: FavoriteCourseEntity)
}
