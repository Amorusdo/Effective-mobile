package com.example.auth_ui._.data.repository

import com.example.auth_ui._.data.mapper.AuthMapper.toDomain
import com.example.auth_ui._.data.remote.AuthApi
import com.example.auth_ui._.data.remote.dto.LoginRequest
import com.example.auth_ui._.data.remote.dto.RegisterRequest
import com.example.auth_ui._.domain.model.User
import com.example.auth_ui._.domain.repository.AuthRepository
import com.example.domain.error.DomainError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AuthRepositoryImpl(
    private val authApi: AuthApi,
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authApi.login(
                    LoginRequest(email = email, password = password)
                )

                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()!!.toDomain()
                    // Сохраните токен
                    // tokenStorage.saveToken(user.token)
                    Result.success(user)
                } else {
                    Result.failure(DomainError.CourseNotFound)
                }
            } catch (e: Exception) {
                Result.failure(DomainError.NetworkError)
            }
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String
    ): Result<User> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authApi.register(
                    RegisterRequest(email = email, password = password, name = name)
                )

                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()!!.toDomain()
                    // Сохраните токен
                    // tokenStorage.saveToken(user.token)
                    Result.success(user)
                } else {
                    Result.failure(DomainError.InvalidEmail)
                }
            } catch (e: Exception) {
                Result.failure(DomainError.ServerError)
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authApi.logout()
                if (response.isSuccessful) {
                    // Удалите токен
                    // tokenStorage.clearToken()
                    Result.success(Unit)
                } else {
                    Result.failure(DomainError.LogoutFailed)
                }
            } catch (e: Exception) {
                // Даже если запрос не удался, очистите локальные данные
                // tokenStorage.clearToken()
                Result.success(Unit)
            }
        }
    }

    override suspend fun getCurrentUser(): Result<User?> {
        return withContext(Dispatchers.IO) {
            try {
                // Проверьте наличие сохраненного токена
                // val token = tokenStorage.getToken()
                // if (token != null) {
                //     // Запросите данные пользователя с сервера
                //     Result.success(user)
                // } else {
                //     Result.success(null)
                // }
                Result.success(null)
            } catch (e: Exception) {
                Result.failure(DomainError.UserDataFetchFailed)
            }
        }
    }
}