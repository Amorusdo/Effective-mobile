package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.FavoritesRepository

/**
 * UseCase для добавления курса в избранное
 */
class AddToFavoritesUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(course: Course) {
        repository.addToFavorites(course)
    }
}