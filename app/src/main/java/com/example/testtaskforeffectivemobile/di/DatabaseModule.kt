package com.example.testtaskforeffectivemobile.di

import android.app.Application
import androidx.room.Room
import com.example.core.core_database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }
}
