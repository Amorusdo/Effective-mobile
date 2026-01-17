package com.example.domain.usecase

import com.example.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow


/**
 * UseCase для проверки, находится ли курс в избранном
 */
class IsCourseInFavoritesUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(courseId: String): Flow<Boolean> {
        return repository.isFavorite(courseId)
    }
}