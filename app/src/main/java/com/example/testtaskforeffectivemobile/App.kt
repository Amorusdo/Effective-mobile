package com.example.testtaskforeffectivemobile

import android.app.Application
import com.example.testtaskforeffectivemobile.di.AppComponent
import com.example.testtaskforeffectivemobile.di.DaggerAppComponent
import kotlin.getValue


class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}

