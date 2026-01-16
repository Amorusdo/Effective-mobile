package com.example.courses.widget

import GreenButton
import SurfaceDark
import TextGray
import TextHint
import TextWhite
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.core_ui.theme.f1
import com.example.core_ui.theme.padding16
import com.example.core_ui.theme.padding56
import com.example.core_ui.theme.padding8
import com.example.core_ui.theme.size12
import com.example.core_ui.theme.size28
import com.example.core_ui.theme.size32
import com.example.core_ui.theme.size56
import com.example.core_ui.theme.textSize16
import com.example.core_ui.theme.textSize20
import com.example.core_ui.theme.width8
import com.example.courses.R

@Composable
fun SearchAndFilterBar(
    isSortedByDate: Boolean,
    onSortClick: () -> Unit,
    searchQuery: String,
    onSearchChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding16),
            horizontalArrangement = Arrangement.spacedBy(size12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Поле поиска
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_courses) ,
                        color = TextHint
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search) ,
                        tint = TextGray
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = SurfaceDark,
                    unfocusedContainerColor = SurfaceDark,
                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite,
                    cursorColor = GreenButton
                ),
                shape = RoundedCornerShape(size28),
                modifier = Modifier
                    .weight(f1)
                    .height(padding56),
                singleLine = true
            )

            // Кнопка фильтра
            Box(
                modifier = Modifier
                    .size(size56)
                    .clip(CircleShape)
                    .background(SurfaceDark)
                    .clickable { /* фильтр неактивен */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter =  painterResource(R.drawable.ic_funnel) ,
                    contentDescription = stringResource(R.string.filter) ,
                    tint = TextWhite,
                    modifier = Modifier.size(size32)
                )
            }
        }

        // Кнопка сортировки
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = padding8 , bottom = padding8)
                .clickable { onSortClick() },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.by_date_added) ,
                color = GreenButton,
                fontSize = textSize16
            )
            Spacer(modifier = Modifier.width(width8))
            Text(
                text = if (isSortedByDate) stringResource(R.string.arrow_botton) else stringResource(
                    R.string.arrow_top
                ) ,
                color = GreenButton,
                fontSize = textSize20
            )
        }
    }
}