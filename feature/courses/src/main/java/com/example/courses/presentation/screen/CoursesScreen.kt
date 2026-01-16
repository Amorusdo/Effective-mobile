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
import androidx.compose.ui.res.stringResource
import com.example.core_ui.theme.height16
import com.example.core_ui.theme.padding16
import com.example.core_ui.theme.padding8
import com.example.core_ui.theme.size12
import com.example.core_ui.theme.textSize16
import com.example.courses.R
import com.example.courses.widget.SearchAndFilterBar
import com.example.ui.cards.CourseCard


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
                            text = uiState.error ?: stringResource(R.string.not_correct) ,
                            color = Red
                        )
                        Spacer(modifier = Modifier.height(height16))
                        Button(
                            onClick = { viewModel.loadCourses() } ,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GreenButton
                            )
                        ) {
                            Text(stringResource(R.string.repeat))
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
                            text = stringResource(R.string.courses_not_found) ,
                            color = TextGray ,
                            fontSize = textSize16
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize() ,
                        contentPadding = PaddingValues(horizontal = padding16 , vertical = padding8) ,
                        verticalArrangement = Arrangement.spacedBy(size12)
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
