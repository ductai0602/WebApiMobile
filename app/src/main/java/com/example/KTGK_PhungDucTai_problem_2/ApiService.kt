package com.example.KTGK_PhungDucTai_problem_2

import retrofit2.http.*
import retrofit2.Call

interface ApiService {
    @GET("todos")
    fun getTodos(): Call<List<Todo>>

    @GET("todos/{id}")
    fun getTodo(@Path("id") id: Int): Call<Todo>

    @POST("todos")
    fun createTodo(@Body todo: Todo): Call<Todo>

    @PUT("todos/{id}")
    fun updateTodo(@Path("id") id: Int, @Body todo: Todo): Call<Todo>

    @DELETE("todos/{id}")
    fun deleteTodo(@Path("id") id: Int): Call<Void>
}
