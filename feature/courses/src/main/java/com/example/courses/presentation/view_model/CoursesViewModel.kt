package com.example.courses.presentation.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CoursesRepository
import com.example.courses.presentation.model.CoursesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CoursesViewModel(
    private val repository: CoursesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoursesUiState())
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            repository.getCourses()
                .onSuccess { courses ->
                    _uiState.update {
                        it.copy(
                            courses = courses,
                            isLoading = false,
                            isSortedByDate = false
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Ошибка загрузки"
                        )
                    }
                }
        }
    }

    fun toggleSort() {
        val currentCourses = _uiState.value.courses
        val sorted = if (_uiState.value.isSortedByDate) {
            // Вернуть к исходному порядку
            currentCourses.sortedBy { it.id }
        } else {
            // Сортировка по publishDate (убывание)
            currentCourses.sortedByDescending { course ->
                try {
                    LocalDateTime.parse(course.publishDate, DateTimeFormatter.ISO_DATE_TIME)
                } catch (e: Exception) {
                    LocalDateTime.MIN
                }
            }
        }

        _uiState.update {
            it.copy(
                courses = sorted,
                isSortedByDate = !it.isSortedByDate
            )
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            repository.toggleFavorite(course)

            // Обновляем UI локально
            _uiState.update { state ->
                state.copy(
                    courses = state.courses.map {
                        if (it.id == course.id) {
                            it.copy(isFavorite = !it.isFavorite)  // ← переключаем
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }
}