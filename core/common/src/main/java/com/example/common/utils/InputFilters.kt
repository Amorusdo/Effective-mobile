package com.example.common.utils

object InputFilters {

    // Набор разрешенных символов для email
    private const val EMAIL_ALLOWED_CHARS = "@._-+"


    // Фильтрует строку, оставляя только символы, допустимые для email

    fun filterEmail(input: String): String {
        return input.filter { char ->
            char in 'a'..'z' ||
                    char in 'A'..'Z' ||
                    char in '0'..'9' ||
                    char in EMAIL_ALLOWED_CHARS
        }
    }


    // Фильтрует строку, оставляя только ASCII символы

    fun filterAsciiOnly(input: String): String {
        return input.filter { it.code < 128 }
    }


     // Фильтрует строку, оставляя только латиницу и цифры

    fun filterAlphanumeric(input: String): String {
        return input.filter { char ->
            char in 'a'..'z' ||
                    char in 'A'..'Z' ||
                    char in '0'..'9'
        }
    }
}