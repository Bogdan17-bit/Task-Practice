package com.example.myapplication.api.repository

import com.example.myapplication.api.requests.ApiService

class UserRepository constructor(private val apiService: ApiService) {
    suspend fun repoGetUsers(page : Int) = apiService.getUsers(page = page)
}