package com.example.springbootandroidquiz.data

import com.example.springbootandroidquiz.data.models.Quiz
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface QuizApi {

    @GET("/api/quizzes") // Updated endpoint
    fun getQuizzes(): Call<List<Quiz>>

    companion object {
        private const val BASE_URL = "http://your-spring-boot-backend-url/"

        fun create(): QuizApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(QuizApi::class.java)
        }
    }
}
