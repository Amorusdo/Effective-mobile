package com.example.courses.domain.mapper

import com.example.core.core_database.entity.FavoriteCourseEntity
import com.example.courses.domain.model.Course

fun FavoriteCourseEntity.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        description = description,
        price = price,
        rating = rating,
        startDate = startDate,
        isFavorite = true, // если в БД, значит избранное
        publishDate = publishDate
    )
}