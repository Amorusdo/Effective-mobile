package com.example.core_data.di

import com.example.core_data.repository.CoursesRepositoryImpl
import com.example.core_data.repository.FavoritesRepositoryImpl
import com.example.domain.repository.CoursesRepository
import com.example.domain.repository.FavoritesRepository
import org.koin.dsl.module

/**
 * Модуль Koin для Data слоя
 * Содержит репозитории
 */
val dataModule = module {

    // Репозиторий для избранного (Singleton, т.к. нужен общий стейт)
    single<FavoritesRepository> { FavoritesRepositoryImpl() }

    single<CoursesRepository> { CoursesRepositoryImpl(apiService = get(), favoriteCourseDao = get()) }


}