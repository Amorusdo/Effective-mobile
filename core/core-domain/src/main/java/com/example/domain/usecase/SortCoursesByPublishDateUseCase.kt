package com.example.domain.usecase

import com.example.domain.model.Course
import java.text.SimpleDateFormat
import java.util.Locale


/**
 * UseCase для сортировки курсов по дате публикации (убывание)
 * Бизнес-логика сортировки вынесена из Repository
 */
@Suppress("DEPRECATION")
class SortCoursesByPublishDateUseCase {

    private val dateFormat = SimpleDateFormat("dd MMMM yyyy" , Locale("ru"))

    operator fun invoke(courses: List<Course>): List<Course> {
        return courses.sortedByDescending { course ->
            try {
                dateFormat.parse(course.publishDate)?.time ?: 0L
            } catch (e: Exception) {
                0L
            }
        }
    }
}