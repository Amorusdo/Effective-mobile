package com.example.courses.widget

import Green
import GreenButton
import SurfaceDarkForCourses
import White
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.theme.Typography
import com.example.courses.R
import com.example.courses.domain.model.Course
import com.example.courses.utils.DateFormatter

@Composable
fun CourseCard(
    course: Course,
    onFavoriteClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Изображение с элементами поверх
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                // Фоновое изображение
                Image(
                    painter = painterResource(R.drawable.pic_cover),
                    contentDescription = "cover",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Закладка в правом верхнем углу
                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = SurfaceDarkForCourses,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = if (course.isFavorite) {
                                Icons.Default.Bookmark
                            } else {
                                Icons.Default.BookmarkBorder
                            } ,
                            contentDescription = "Избранное" ,
                            tint = if (course.isFavorite) {
                                Green
                            } else {
                                White
                            }
                        )
                     }
                }

                // Рейтинг и дата в левом нижнем углу
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Рейтинг
                    Box(
                        modifier = Modifier
                            .background(
                                color = SurfaceDarkForCourses,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_star),
                                contentDescription = "star",
                                tint = Green,
                                modifier = Modifier.size(16.dp)
                            )

                            Text(
                                text = course.rating.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                color = SurfaceDarkForCourses,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                    // Дата
                    Text(
                        text = DateFormatter.formatDate(course.publishDate),
                        style = MaterialTheme.typography.bodySmall,
                        color = White,
                        fontSize = 12.sp
                    )
                }}
            }

            // Контент под изображением
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Заголовок
                Text(
                    text = course.title,
                    color = White,
                    style = Typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Описание (макс 2 строки)
                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${course.price} ₽",
                        style = MaterialTheme.typography.titleMedium,
                        color = White,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Подробнее",
                            style = MaterialTheme.typography.titleMedium,
                            color = GreenButton,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "➔",
                            color = GreenButton,
                            fontSize = 16.sp
                        )
                    }
                }

            }
        }
    }
}