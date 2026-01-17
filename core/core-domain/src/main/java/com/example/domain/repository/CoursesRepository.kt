package com.example.domain.repository


import com.example.domain.model.Course

interface CoursesRepository {
    suspend fun getCourses(): Result<List<Course>>
    suspend fun getFavoriteCourses(): Result<List<Course>>
    suspend fun addToFavorites(courseId: String): Result<Unit>
    suspend fun removeFromFavorites(courseId: String): Result<Unit>
    suspend fun isCourseInFavorites(courseId: String): Result<Boolean>
}