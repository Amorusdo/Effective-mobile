package com.example.domain.usecase

import com.example.domain.repository.FavoritesRepository

/**
 * UseCase для удаления курса из избранного
 */
class RemoveFromFavoritesUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(courseId: String) {
        repository.removeFromFavorites(courseId)
    }
}