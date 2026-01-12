package com.example.core.core_database.dao

import androidx.room.*
import com.example.core.core_database.entity.FavoriteCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCourseDao {

    @Query("SELECT * FROM favorite_courses ORDER BY addedAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteCourseEntity>>

    @Query("SELECT * FROM favorite_courses WHERE id = :courseId")
    suspend fun getFavoriteById(courseId: String): FavoriteCourseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(course: FavoriteCourseEntity)

    @Delete
    suspend fun deleteFavorite(course: FavoriteCourseEntity)

    @Query("DELETE FROM favorite_courses WHERE id = :courseId")
    suspend fun deleteFavoriteById(courseId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_courses WHERE id = :courseId)")
    suspend fun isFavorite(courseId: String): Boolean
}