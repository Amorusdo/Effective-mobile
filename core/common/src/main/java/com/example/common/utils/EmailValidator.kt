package com.example.common.utils

object EmailValidator {

    private val EMAIL_REGEX =
        "^[A-Za-z0-9+_.\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$".toRegex()

    fun isValid(email: String): Boolean {
        // Проверяем, что строка содержит только ASCII символы
        return EMAIL_REGEX.matches(email) && email.all { it.code < 128 }
    }
}