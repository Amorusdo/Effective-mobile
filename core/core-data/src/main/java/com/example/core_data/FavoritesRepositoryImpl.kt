package com.example.core_data

import com.example.domain.Course
import com.example.domain.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl : FavoritesRepository {

    private val _favorites = MutableStateFlow<List<Course>>(emptyList())

    override fun getFavorites(): Flow<List<Course>> = _favorites

    override suspend fun isFavorite(courseId: String): Flow<Boolean> {
        return _favorites.map { favorites ->
            favorites.any { it.id == courseId }
        }
    }

    override suspend fun addToFavorites(course: Course) {
        val updated = course.copy(isFavorite = true)
        _favorites.value = _favorites.value + updated
    }

    override suspend fun removeFromFavorites(courseId: String) {
        _favorites.value = _favorites.value.filter { it.id != courseId }
    }

    override suspend fun toggleFavorite(course: Course) {
        val isFav = _favorites.value.any { it.id == course.id }
        if (isFav) {
            removeFromFavorites(course.id)
        } else {
            addToFavorites(course)
        }
    }
}