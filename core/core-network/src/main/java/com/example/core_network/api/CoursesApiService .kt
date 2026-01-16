package com.example.core_network.api


import com.example.common.constant.ApiConfig.DATABASE
import com.example.core_network.model.CoursesResponse
import retrofit2.http.GET

interface CoursesApiService {
    @GET( DATABASE )
    suspend fun getCourses(): CoursesResponse
}