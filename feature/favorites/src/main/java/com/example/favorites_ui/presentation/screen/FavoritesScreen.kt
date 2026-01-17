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
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.core_ui.theme.Typography
import com.example.core_ui.theme.height16
import com.example.core_ui.theme.padding16
import com.example.core_ui.theme.padding40
import com.example.core_ui.theme.padding8
import com.example.core_ui.theme.size12
import com.example.favorites_ui.R
import com.example.favorites_ui.presentation.view_model.FavoritesViewModel
import com.example.ui.cards.CourseCard


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
            .padding(top = padding40 , start = padding16) ,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            // Заголовок "Вход"
            Text(
                text = stringResource(com.example.common.R.string.favourites) ,
                style = MaterialTheme.typography.headlineLarge ,
                color = TextWhite ,
                modifier = Modifier.padding(bottom = padding16)
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

                            Spacer(modifier = Modifier.height(height16))
                            Text(
                                text = stringResource(R.string.there_are_no_selected_courses) ,
                                style = Typography.titleMedium ,
                                color = Gray
                            )
                            Spacer(modifier = Modifier.height(padding8))
                            Text(
                                text = stringResource(R.string.add_courses_to_your_favorites) ,
                                style = Typography.titleMedium,
                                color = Gray ,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                else -> {
                    // Список избранных курсов
                    LazyColumn(
                        modifier = Modifier.fillMaxSize() ,
                        verticalArrangement = Arrangement.spacedBy(size12)
                    ) {
                        items(
                            items = uiState.favorites ,
                            key = { course -> course.id }
                        ) { course ->
                         CourseCard(
                                course = course ,
                                onFavoriteClick = { viewModel.removeFavorite(course) },
                            )
                        }
                    }
                }
            }
        }
    }
}

