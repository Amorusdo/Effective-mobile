package com.example.favorites_ui.presentation.model

import com.example.domain.Course


data class FavoritesUiState(
    val favorites: List<Course> = emptyList() ,
    val isLoading: Boolean = false
)