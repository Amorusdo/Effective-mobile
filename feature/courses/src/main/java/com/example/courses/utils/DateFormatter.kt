@file:Suppress("DEPRECATION")

package com.example.courses.utils


import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {

    private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    fun formatDate(dateString: String): String {
        return try {
            val date = LocalDate.parse(dateString, inputFormatter)
            date.format(outputFormatter)
        } catch (_: Exception) {
            dateString
        }
    }
}