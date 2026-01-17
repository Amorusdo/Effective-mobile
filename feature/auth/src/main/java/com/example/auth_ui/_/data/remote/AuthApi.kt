package com.example.auth_ui._.data.remote

import com.example.auth_ui._.data.remote.dto.LoginRequest
import com.example.auth_ui._.data.remote.dto.LoginResponse
import com.example.auth_ui._.data.remote.dto.RegisterRequest
import com.example.auth_ui._.data.remote.dto.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>
}