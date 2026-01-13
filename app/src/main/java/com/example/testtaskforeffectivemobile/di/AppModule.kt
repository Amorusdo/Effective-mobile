@file:Suppress("DEPRECATION")

package com.example.testtaskforeffectivemobile.di



import CoursesViewModel
import androidx.room.Room
import com.example.auth_ui.presentation.view_model.AuthViewModel
import com.example.core.core_database.database.AppDatabase
import com.example.core_network.api.CoursesApiService
import com.example.courses.domain.repository.CoursesRepositoryImpl
import com.example.courses.domain.repository.CoursesRepository
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
    single<OkHttpClient> {  // ← указываем тип явно
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit
    single<Retrofit> {  // ← указываем тип явно
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .client(get())  // теперь Koin знает, что нужен OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API Service
    single<CoursesApiService> {  // ← указываем тип явно
        get<Retrofit>().create(CoursesApiService::class.java)
    }

    // Room Database
    single<AppDatabase> {  // ← указываем тип явно
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

    // Repository
    single<CoursesRepository> {
        CoursesRepositoryImpl(
            apiService = get() ,
            favoriteCourseDao = get()
        )
    }

    // ViewModels
    viewModel { AuthViewModel() }
    viewModel { CoursesViewModel(repository = get()) }
    viewModel { FavoritesViewModel(repository = get()) }

}