package com.example.auth_ui._.data.mapper

import com.example.auth_ui._.data.remote.dto.LoginResponse
import com.example.auth_ui._.data.remote.dto.RegisterResponse
import com.example.auth_ui._.data.remote.dto.UserDto
import com.example.auth_ui._.domain.model.User


object AuthMapper {

    fun LoginResponse.toDomain(): User {
        return User(
            id = user.id,
            email = user.email,
            name = user.name,
            token = token
        )
    }

    fun RegisterResponse.toDomain(): User {
        return User(
            id = user.id,
            email = user.email,
            name = user.name,
            token = token
        )
    }

    fun UserDto.toDomain(token: String): User {
        return User(
            id = id,
            email = email,
            name = name,
            token = token
        )
    }
}