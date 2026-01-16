package com.example.core.core_database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.common.constant.ApiConfig.FAVORITE_COURSE

@Entity(tableName = FAVORITE_COURSE)
data class FavoriteCourseEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val rating: Double,
    val startDate: String,
    val publishDate: String,
    val addedAt: Long = System.currentTimeMillis()
)