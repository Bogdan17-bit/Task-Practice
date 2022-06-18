package com.example.myapplication.di

import com.example.myapplication.api.requests.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val serviceModule = module {
    single {
        createRetrofit(createOkHttpClient()) // створює ретрофіт, приймає клієнт в параметр
    }
    single {
        createNetworkApi(get())  // створює сервіс, приймає об'єкт ретрофіту, тому get()
    }
}

fun createNetworkApi(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://randomuser.me/api/")
        .client(okHttpClient)
        .build()
}