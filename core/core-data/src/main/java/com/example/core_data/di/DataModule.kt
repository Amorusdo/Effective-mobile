package com.example.core_data.di

import com.example.core_data.FavoritesRepositoryImpl
import com.example.domain.FavoritesRepository
import org.koin.dsl.module


val dataModule = module {
    single<FavoritesRepository> { FavoritesRepositoryImpl() }
}