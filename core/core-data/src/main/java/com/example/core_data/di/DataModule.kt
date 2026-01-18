package com.example.core_data.di

import com.example.core_data.repository.CoursesRepositoryImpl
import com.example.core_data.repository.FavoritesRepositoryImpl
import com.example.domain.repository.CoursesRepository
import com.example.domain.repository.FavoritesRepository
import org.koin.dsl.module

val dataModule = module {

    single<FavoritesRepository> { FavoritesRepositoryImpl() }
    single<CoursesRepository> { CoursesRepositoryImpl(apiService = get(), favoriteCourseDao = get()) }


}