package com.example.courses.presentation.model

import com.example.domain.Course

data class CoursesUiState(
    val courses: List<Course> = emptyList() ,
    val filteredCourses: List<Course> = emptyList() ,
    val searchQuery: String = "" ,
    val isLoading: Boolean = false ,
    val error: String? = null ,
    val isSortedByDate: Boolean = false
)