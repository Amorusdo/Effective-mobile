package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.FavoritesRepository

/**
 * UseCase для добавления/удаления курса из избранного
 */
class ToggleFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(course: Course) {
        repository.toggleFavorite(course)
    }
}