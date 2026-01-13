package com.example.favorites_ui.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.domain.model.Course
import com.example.courses.domain.repository.CoursesRepository
import com.example.favorites_ui.presentation.model.FavoritesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: CoursesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            repository.getFavoriteCourses().collect { favorites ->
                _uiState.value = FavoritesUiState(favorites = favorites)
            }
        }
    }

    fun removeFavorite(course: Course) {
        viewModelScope.launch {
            repository.toggleFavorite(course)
        }
    }
}