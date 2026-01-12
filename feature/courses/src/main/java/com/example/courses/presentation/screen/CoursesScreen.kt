package com.example.courses.presentation.screen

import GreenButton
import White
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.theme.Typography
import com.example.courses.domain.model.Course
import com.example.courses.presentation.view_model.CoursesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(
    viewModel: CoursesViewModel ,
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Верхняя панель с поиском и фильтром
        TopAppBar(
            title = {
                Text("Курсы")
            } ,
            actions = {
                // Иконка поиска (неактивная)
                IconButton(onClick = { /* неактивна */ }) {
                    Icon(
                        imageVector = Icons.Default.Search ,
                        contentDescription = "Поиск" ,
                        tint = Gray
                    )
                }

                // Кнопка сортировки
                TextButton(
                    onClick = { viewModel.toggleSort() }
                ) {
                    Text(
                        text = if (uiState.isSortedByDate) "⬇ Дата" else "Сортировка" ,
                        fontSize = 14.sp
                    )
                }
            }
        )

        // Контент
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize() ,
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
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
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.loadCourses() }) {
                            Text("Повторить")
                        }
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize() ,
                    contentPadding = PaddingValues(16.dp) ,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.courses) { course ->
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

@Composable
fun CourseCard(
    course: Course ,
    onFavoriteClick: () -> Unit ,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* клик на курс */ } ,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.Top
            ) {
                // Заголовок
                Text(
                    text = course.title ,
                    color = White,
                    style = Typography.titleMedium ,
                    modifier = Modifier.weight(1f)
                )

                // Иконка избранного
                IconButton(
                    onClick = onFavoriteClick ,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (course.isFavorite) {
                            Icons.Default.Bookmark
                        } else {
                            Icons.Default.BookmarkBorder
                        } ,
                        contentDescription = "Избранное" ,
                        tint = if (course.isFavorite) {
                            GreenButton
                        } else {
                            White
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Описание (макс 2 строки)
            Text(
                text = course.description ,
                style = MaterialTheme.typography.bodyMedium ,
                maxLines = 2 ,
                overflow = TextOverflow.Ellipsis ,
                color = Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Нижняя панель: цена и рейтинг
            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Цена
                Text(
                    text = course.price ,
                    style = MaterialTheme.typography.titleMedium ,
                    color = White,
                )

                // Рейтинг
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⭐" ,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = course.rating.toString() ,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}