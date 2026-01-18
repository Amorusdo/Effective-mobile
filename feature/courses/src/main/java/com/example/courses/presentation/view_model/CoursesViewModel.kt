package com.example.courses.presentation.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.toUserMessage
import com.example.courses.presentation.model.CoursesUiState
import com.example.domain.model.Course
import com.example.domain.usecase.GetCoursesUseCase
import com.example.domain.usecase.SortCoursesByPublishDateUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана списка курсов
 * Использует UseCase'ы вместо прямого обращения к Repository
 */
class CoursesViewModel(
    application: Application,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val sortCoursesByPublishDateUseCase: SortCoursesByPublishDateUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) :  AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(CoursesUiState())
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()

    private var allCourses: List<Course> = emptyList()

    init {
        loadCourses()

    }

    /**
     * Загрузка курсов с сервера
     */
    fun loadCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getCoursesUseCase()
                .onSuccess { courses ->
                    allCourses = courses
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            courses = courses,
                            filteredCourses = applyFilters(courses)
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUserMessage(getApplication()),
                        )
                    }
                }
        }
    }

    /**
     * Переключение сортировки по дате
     */
    fun toggleSort() {
        _uiState.update { currentState ->
            val newSortState = !currentState.isSortedByDate
            val sortedCourses = if (newSortState) {
                sortCoursesByPublishDateUseCase(currentState.courses)
            } else {
                allCourses
            }

            currentState.copy(
                isSortedByDate = newSortState,
                courses = sortedCourses,
                filteredCourses = applyFilters(sortedCourses)
            )
        }
    }

    /**
     * Обработка изменения поискового запроса
     */
    fun onSearchChange(query: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = query,
                filteredCourses = applyFilters(currentState.courses)
            )
        }
    }

    /**
     * Применение фильтров (поиск)
     */
    private fun applyFilters(courses: List<Course>): List<Course> {
        val query = _uiState.value.searchQuery

        if (query.isBlank()) {
            return courses
        }

        return courses.filter { course ->
            course.title.contains(query, ignoreCase = true) ||
                    course.description.contains(query, ignoreCase = true)

        }
    }

    /**
     * Переключение статуса избранного для курса
     */
    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)

            // Обновляем UI
            _uiState.update { currentState ->
                val updatedCourses = currentState.courses.map {
                    if (it.id == course.id) {
                        it.copy(isFavorite = !it.isFavorite)
                    } else {
                        it
                    }
                }

                currentState.copy(
                    courses = updatedCourses,
                    filteredCourses = applyFilters(updatedCourses)
                )
            }
        }
    }
}

