@file:Suppress("DEPRECATION")

package com.example.testtaskforeffectivemobile.di

import androidx.room.Room
import com.example.common.constant.ApiConfig.BASE_URL
import com.example.core.core_database.database.AppDatabase
import com.example.core_data.repository.CoursesRepositoryImpl
import com.example.core_data.repository.FavoritesRepositoryImpl
import com.example.core_network.api.CoursesApiService
import com.example.core_ui.theme.INT_30

import com.example.courses.presentation.view_model.CoursesViewModel
import com.example.domain.repository.CoursesRepository
import com.example.domain.repository.FavoritesRepository
import com.example.favorites_ui.presentation.view_model.FavoritesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    // OkHttp
    single<OkHttpClient> {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(INT_30, TimeUnit.SECONDS)
            .readTimeout(INT_30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API Service
    single<CoursesApiService> {
        get<Retrofit>().create(CoursesApiService::class.java)
    }

    // Room Database
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .build()
    }

    // DAO
    single {
        get<AppDatabase>().favoriteCourseDao()
    }

    // Repositories
    single<CoursesRepository> {
        CoursesRepositoryImpl(
            apiService = get() ,
            favoriteCourseDao = get()
        )
    }

    single<FavoritesRepository> {
        FavoritesRepositoryImpl()
    }

    // ViewModels (убрали AuthViewModel отсюда)
    viewModel {
        CoursesViewModel(
            getCoursesUseCase = get(),
            sortCoursesByPublishDateUseCase = get(),
            toggleFavoriteUseCase = get()
        )
    }

    viewModel {
        FavoritesViewModel(
            getFavoriteCoursesUseCase = get(),
            removeFromFavoritesUseCase = get(),
            toggleFavoriteUseCase = get()
        )
    }
}