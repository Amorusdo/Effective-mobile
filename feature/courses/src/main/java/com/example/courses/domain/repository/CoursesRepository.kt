package com.example.courses.domain.repository

import com.example.domain.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun getCourses(): Result<List<Course>>

    suspend fun toggleFavorite(course: Course)

    fun getFavoriteCourses(): Flow<List<Course>>
}