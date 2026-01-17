package com.example.favorites_ui.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import com.example.domain.usecase.GetFavoriteCoursesUseCase
import com.example.domain.usecase.RemoveFromFavoritesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана избранных курсов
 */
class FavoritesViewModel(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    /**
     * Загрузка избранных курсов
     */
    private fun loadFavorites() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getFavoriteCoursesUseCase()
                .collect { favorites ->
                    _uiState.update {
                        it.copy(
                            favorites = favorites,
                            isLoading = false
                        )
                    }
                }
        }
    }

    /**
     * Удаление курса из избранного
     */
    fun removeFavorite(course: Course) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(course.id)
        }
    }

    /**
     * Переключение статуса избранного
     */
    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
        }
    }
}

/**
 * UI состояния для экрана избранного
 */
data class FavoritesUiState(
    val favorites: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)