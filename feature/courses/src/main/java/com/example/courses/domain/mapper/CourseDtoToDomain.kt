package com.example.courses.domain.mapper

import com.example.core_network.model.CourseDto
import com.example.domain.Course

fun CourseDto.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        description = description,
        price = price,
        rating = rating,
        startDate = startDate,
        isFavorite = isFavorite,
        publishDate = publishDate
    )
}