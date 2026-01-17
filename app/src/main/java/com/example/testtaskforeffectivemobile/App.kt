package com.example.testtaskforeffectivemobile

import android.app.Application
import com.example.testtaskforeffectivemobile.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.example.auth_ui._.di.authModule
import com.example.core_data.di.dataModule
import com.example.domain.di.domainModule


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                appModule,
                dataModule,
                domainModule,   // UseCase для курсов
                authModule      // Авторизация
            )
        }
    }
}