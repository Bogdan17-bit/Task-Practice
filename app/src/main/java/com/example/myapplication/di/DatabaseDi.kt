package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.room.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        getAppDatabase(get())
    }
}

fun getAppDatabase(context : Context) : AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()
}
