package com.example.myapplication.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.UserDatabaseDao
import com.example.myapplication.room.dbrepository.UserRepositoryDb
import org.koin.dsl.module

val databaseModule = module {
    single {
        UserRepositoryDb(getUserDao(get()))
    }
    single {
        AppDatabase.getDatabase(get())
    }
}

fun getUserDao(context : Context) : UserDatabaseDao {
    return AppDatabase.getDatabase(context).userDao()
}
