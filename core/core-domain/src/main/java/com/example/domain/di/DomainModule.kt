package com.example.domain.di

import com.example.domain.usecase.*
import org.koin.dsl.module


/**
 * Модуль Koin для Domain слоя
 * Содержит все UseCase'ы приложения
 */
val domainModule = module {

    // UseCase для работы с курсами
    factory { GetCoursesUseCase(repository = get()) }

    factory { SortCoursesByPublishDateUseCase() }

    // UseCase для работы с избранным
    factory { GetFavoriteCoursesUseCase(repository = get()) }

    factory { ToggleFavoriteUseCase(repository = get()) }

    factory { AddToFavoritesUseCase(repository = get()) }

    factory { RemoveFromFavoritesUseCase(repository = get()) }

    factory { IsCourseInFavoritesUseCase(repository = get()) }
}