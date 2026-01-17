package com.example.core_data.mapper

import com.example.core.core_database.entity.FavoriteCourseEntity
import com.example.domain.model.Course

fun Course.toFavoriteEntity(): FavoriteCourseEntity {
    return FavoriteCourseEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        rating = rating,
        startDate = startDate,
        publishDate = publishDate
    )
}

