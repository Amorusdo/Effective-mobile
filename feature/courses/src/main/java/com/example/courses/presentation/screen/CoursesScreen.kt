package com.example.courses.presentation.screen

import Black
import CoursesViewModel
import GreenButton
import Red
import TextGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courses.widget.CourseCard
import com.example.courses.widget.SearchAndFilterBar


@Composable
fun CoursesScreen(
    viewModel: CoursesViewModel ,
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        // Верхняя панель с поиском и фильтром
        SearchAndFilterBar(
            isSortedByDate = uiState.isSortedByDate ,
            onSortClick = { viewModel.toggleSort() } ,
            searchQuery = uiState.searchQuery ,  // ← передаём
            onSearchChange = { viewModel.onSearchChange(it) }  // ← передаём
        )

        // Контент
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize() ,
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = GreenButton)
                }
            }

            uiState.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize() ,
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = uiState.error ?: "Ошибка" ,
                            color = Red
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.loadCourses() } ,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GreenButton
                            )
                        ) {
                            Text("Повторить")
                        }
                    }
                }
            }

            else -> {
                if (uiState.filteredCourses.isEmpty()) {
                    // Пустое состояние при поиске
                    Box(
                        modifier = Modifier.fillMaxSize() ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Курсы не найдены" ,
                            color = TextGray ,
                            fontSize = 16.sp
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize() ,
                        contentPadding = PaddingValues(horizontal = 16.dp , vertical = 8.dp) ,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = uiState.filteredCourses ,
                            key = { course -> course.id }
                        ) { course ->
                            CourseCard(
                                course = course ,
                                onFavoriteClick = { viewModel.toggleFavorite(course) }
                            )
                        }
                    }
                }
            }
        }
    }
}
