package com.example.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.models.database.UserDatabase

@Database(entities = [UserDatabase::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao() : UserDatabaseDao
}