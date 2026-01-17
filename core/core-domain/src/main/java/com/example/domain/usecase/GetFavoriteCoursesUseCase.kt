package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

/**
 * UseCase для получения списка избранных курсов
 */
class GetFavoriteCoursesUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getFavorites()
    }
}