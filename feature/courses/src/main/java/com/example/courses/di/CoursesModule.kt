@file:Suppress("DEPRECATION")

package com.example.courses.di

import CoursesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coursesModule = module {
    viewModel { CoursesViewModel(get(), get()) }
}