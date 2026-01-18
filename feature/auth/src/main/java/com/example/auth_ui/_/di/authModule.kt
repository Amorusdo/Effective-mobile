@file:Suppress("DEPRECATION")

package com.example.auth_ui._.di

import com.example.auth_ui._.data.remote.AuthApi
import com.example.auth_ui._.data.repository.AuthRepositoryImpl
import com.example.auth_ui._.domain.repository.AuthRepository
import com.example.auth_ui._.domain.usecase.LoginUseCase
import com.example.auth_ui._.domain.usecase.RegisterUseCase
import com.example.auth_ui._.domain.usecase.LogoutUseCase
import com.example.auth_ui._.presentation.view_model.AuthViewModel
import com.example.auth_ui._.presentation.view_model.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    // API
    single<AuthApi> {
        get<Retrofit>().create(AuthApi::class.java)
    }

    // Repository
    single<AuthRepository> {
        AuthRepositoryImpl(authApi = get())
    }

    // UseCases
    factory { LoginUseCase(authRepository = get()) }
    factory { RegisterUseCase(authRepository = get()) }
    factory { LogoutUseCase(authRepository = get()) }

    // ViewModels
    viewModel { AuthViewModel(loginUseCase = get()) }
    viewModel { RegisterViewModel(application = get(), registerUseCase = get()) }
}