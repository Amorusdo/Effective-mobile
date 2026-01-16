package com.example.favorites_ui.widget

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.core_ui.theme.Typography
import com.example.core_ui.theme.f1
import com.example.core_ui.theme.height12
import com.example.core_ui.theme.height120
import com.example.core_ui.theme.padding16
import com.example.core_ui.theme.padding2
import com.example.core_ui.theme.padding4
import com.example.core_ui.theme.padding6
import com.example.core_ui.theme.padding8
import com.example.core_ui.theme.size12
import com.example.core_ui.theme.size16
import com.example.core_ui.theme.size24
import com.example.core_ui.theme.size32
import com.example.core_ui.theme.size4
import com.example.core_ui.theme.textSize12
import com.example.core_ui.theme.textSize14
import com.example.core_ui.theme.textSize16
import com.example.core_ui.theme.textSize18
import com.example.core_ui.theme.width6
import com.example.courses.R
import com.example.courses.domain.model.Course
import com.example.courses.utils.DateFormatter.formatDate

@Composable
fun FavoriteCourseCard(
    course: Course ,
    onRemoveFavorite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = padding2)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height120)
            ) {
                Image(
                    painter = painterResource(R.drawable.pic_cover),
                    contentDescription = stringResource(com.example.favorites_ui.R.string.cover) ,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = onRemoveFavorite,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(padding8)
                        .size(size32)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = SurfaceDarkForCourses ,
                                shape = RoundedCornerShape(size12)
                            )
                            .padding(horizontal = padding6 , vertical = padding6)
                    ) {
                        Icon(
                            modifier = Modifier.size(size24),
                            imageVector = if (course.isFavorite) {
                                Icons.Default.Bookmark
                            } else {
                                Icons.Default.BookmarkBorder
                            } ,
                            contentDescription = stringResource(com.example.favorites_ui.R.string.favorite) ,
                            tint = if (course.isFavorite) {
                                Green
                            } else {
                                White
                            }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(padding8),
                    horizontalArrangement = Arrangement.spacedBy(size12),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Рейтинг
                    Box(
                        modifier = Modifier
                            .background(
                                color = SurfaceDarkForCourses ,
                                shape = RoundedCornerShape(size12)
                            )
                            .padding(horizontal = padding8 , vertical = padding4)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(size4)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_star),
                                contentDescription = stringResource(com.example.favorites_ui.R.string.star) ,
                                tint = Green,
                                modifier = Modifier.size(size16)
                            )

                            Text(
                                text = course.rating.toString(),
                                style = Typography.titleMedium,
                                color = White,
                                fontSize = textSize14 ,

                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                color = SurfaceDarkForCourses ,
                                shape = RoundedCornerShape(size12)
                            )
                            .padding(horizontal = padding8 , vertical = padding4)
                    ) {
                        // Дата
                        Text(
                            text = formatDate(course.publishDate),
                            style = Typography.bodySmall,
                            color = White,
                            fontSize = textSize12
                        )
                    }}
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding16)
            ) {
                // Заголовок
                Text(
                    text = course.title,
                    color = White,
                    style = Typography.titleMedium
                )

                Spacer(modifier = Modifier.height(padding8))

                // Описание
                Text(
                    text = course.description,
                    style =Typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Gray
                )

                Spacer(modifier = Modifier.height(height12))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = padding8)
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(
                            R.string.rub ,
                            course.price
                        ) ,
                        style = Typography.titleMedium,
                        color = White,
                        fontSize = textSize18
                    )

                    Spacer(modifier = Modifier.weight(f1))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(com.example.favorites_ui.R.string.more_detailed) ,
                            style =Typography.titleMedium,
                            color = GreenButton,
                            fontSize = textSize16
                        )

                        Spacer(modifier = Modifier.width(width6))

                        Text(
                            text = stringResource(com.example.favorites_ui.R.string.arrow_right) ,
                            color = GreenButton,
                            fontSize = textSize16
                        )
                    }
                }

            }
        }
    }
}