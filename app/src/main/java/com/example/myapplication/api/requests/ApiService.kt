package com.example.myapplication.api.requests

import com.example.myapplication.models.response.JsonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?results=10")
    suspend fun getUsers(@Query("page") page : Int) : Response<JsonResponse>
}