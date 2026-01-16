package com.example.courses.domain.repository

import com.example.core.core_database.dao.FavoriteCourseDao
import com.example.core_network.api.CoursesApiService
import com.example.courses.domain.mapper.toDomain
import com.example.courses.domain.mapper.toFavoriteEntity
import com.example.domain.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoursesRepositoryImpl(
    private val apiService: CoursesApiService ,
    private val favoriteCourseDao: FavoriteCourseDao
) : CoursesRepository {

    override suspend fun getCourses(): Result<List<Course>> {
        return try {
            val response = apiService.getCourses()
            val courses = response.courses.map { dto ->
                val course = dto.toDomain()
                // Проверяем, есть ли курс в избранном
                val isFavorite = favoriteCourseDao.isFavorite(course.id)
                course.copy(isFavorite = isFavorite)
            }
            Result.success(courses)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun toggleFavorite(course: Course) {
        if (course.isFavorite) {
            // Удаляем из избранного
            favoriteCourseDao.deleteFavoriteById(course.id)
        } else {
            // Добавляем в избранное
            favoriteCourseDao.insertFavorite(course.toFavoriteEntity())
        }
    }

    override fun getFavoriteCourses(): Flow<List<Course>> {
        return favoriteCourseDao.getAllFavorites()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }
}