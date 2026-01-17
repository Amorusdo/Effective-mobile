package com.example.favorites_ui.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Course
import com.example.domain.FavoritesRepository
import com.example.favorites_ui.presentation.model.FavoritesUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favoritesRepository.getFavorites().collect { favorites ->
                _uiState.update { it.copy(favorites = favorites) }
            }
        }
    }

    fun removeFavorite(course: Course) {
        viewModelScope.launch {
            favoritesRepository.removeFromFavorites(course.id)
        }
    }
}