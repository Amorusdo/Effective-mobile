package com.example.domain

// core/domain/repository/FavoritesRepository.kt

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<List<Course>>
    suspend fun addToFavorites(course: Course)
    suspend fun removeFromFavorites(courseId: String)
    suspend fun isFavorite(courseId: String): Flow<Boolean>
    suspend fun toggleFavorite(course: Course)
}