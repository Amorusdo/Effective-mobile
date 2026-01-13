package com.example.favorites_ui.presentation.screen

import Black
import TextWhite
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.favorites_ui.presentation.view_model.FavoritesViewModel
import com.example.favorites_ui.widget.FavoriteCourseCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel ,
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(top = 40.dp, start = 16.dp) ,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            // Заголовок "Вход"
            Text(
                text = "Избранное" ,
                style = MaterialTheme.typography.headlineLarge ,
                color = TextWhite ,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Контент
            when {
                uiState.favorites.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize() ,
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "⭐" ,
                                fontSize = 48.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Нет избранных курсов" ,
                                style = MaterialTheme.typography.titleMedium ,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Добавьте курсы в избранное,\nчтобы быстро находить их здесь" ,
                                style = MaterialTheme.typography.bodyMedium ,
                                color = Color.Gray ,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }

                else -> {
                    // Список избранных курсов
                    LazyColumn(
                        modifier = Modifier.fillMaxSize() ,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = uiState.favorites ,
                            key = { course -> course.id }
                        ) { course ->
                            FavoriteCourseCard(
                                course = course ,
                                onRemoveFavorite = { viewModel.removeFavorite(course) }
                            )
                        }
                    }
                }
            }
        }
    }
}

