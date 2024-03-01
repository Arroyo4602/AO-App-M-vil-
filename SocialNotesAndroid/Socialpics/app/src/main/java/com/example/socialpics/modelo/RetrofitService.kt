package com.example.socialpics.modelo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "http://10.66.2.66:9090"
    val instance: ApiService by lazy {
        val usuario = OkHttpClient.Builder().build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(usuario)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(ApiService::class.java)
    }
}