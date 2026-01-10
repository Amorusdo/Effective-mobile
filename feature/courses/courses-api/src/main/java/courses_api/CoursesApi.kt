package courses_api

import retrofit2.http.GET

interface CoursesApi {
    @GET("v3/XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX")
    suspend fun getCourses(): CoursesResponse
}