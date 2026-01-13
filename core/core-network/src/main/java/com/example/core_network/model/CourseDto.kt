package com.example.core_network.model

import com.google.gson.annotations.SerializedName

data class CourseDto(
    @SerializedName("id") val id: String ,
    @SerializedName("title") val title: String ,
    @SerializedName("text") val description: String ,
    @SerializedName("price") val price: String ,
    @SerializedName("rate") val rating: Double ,
    @SerializedName("startDate") val startDate: String ,
    @SerializedName("hasLike") val isFavorite: Boolean ,
    @SerializedName("publishDate") val publishDate: String
)