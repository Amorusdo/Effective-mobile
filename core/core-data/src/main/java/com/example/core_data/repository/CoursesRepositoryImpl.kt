package com.example.core_data.repository

import com.example.core.core_database.dao.FavoriteCourseDao
import com.example.core_data.mapper.toDomain
import com.example.core_data.mapper.toFavoriteEntity
import com.example.core_network.api.CoursesApiService
import com.example.domain.error.DomainError
import com.example.domain.model.Course
import com.example.domain.repository.CoursesRepository

class CoursesRepositoryImpl(
    private val apiService: CoursesApiService ,
    private val favoriteCourseDao: FavoriteCourseDao
) : CoursesRepository {

    override suspend fun getCourses(): Result<List<Course>> {
        return try {
            val response = apiService.getCourses()

            val courses = response.courses.map { dto ->
                val course = dto.toDomain()
                val isFavorite = favoriteCourseDao.isFavorite(course.id)
                course.copy(isFavorite = isFavorite)
            }

            Result.success(courses)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }


    override suspend fun getFavoriteCourses(): Result<List<Course>> {
        return try {
            val favorites = favoriteCourseDao.getAllFavoritesOnce()
                .map { it.toDomain() }
            Result.success(favorites)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addToFavorites(courseId: String): Result<Unit> {
        return try {
            // Сначала получаем курс из API
            val response = apiService.getCourses()
            val course = response.courses
                .find { it.id == courseId }
                ?.toDomain()

            if (course != null) {
                favoriteCourseDao.insertFavorite(course.toFavoriteEntity())
                Result.success(Unit)
            } else {
                Result.failure(DomainError.CourseNotFound)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeFromFavorites(courseId: String): Result<Unit> {
        return try {
            favoriteCourseDao.deleteFavoriteById(courseId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isCourseInFavorites(courseId: String): Result<Boolean> {
        return try {
            val isFavorite = favoriteCourseDao.isFavorite(courseId)
            Result.success(isFavorite)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}