package com.example.myapplication.di

import com.example.myapplication.api.repository.UserRepository
import org.koin.dsl.module

val userRepositoryModule = module {
    single {
        UserRepository(get()) // приймає об'єкт ApiService, тому get()
    }
}