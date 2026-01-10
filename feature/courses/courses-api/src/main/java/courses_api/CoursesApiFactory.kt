package courses_api

import com.example.core_network.RetrofitProvider

object CoursesApiFactory {
    val api: CoursesApi by lazy {
        RetrofitProvider.retrofit.create(CoursesApi::class.java)
    }
}