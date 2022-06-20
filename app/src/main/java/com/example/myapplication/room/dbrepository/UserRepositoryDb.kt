package com.example.myapplication.room.dbrepository

import androidx.lifecycle.LiveData
import com.example.myapplication.models.database.UserDatabase
import com.example.myapplication.models.response.User
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.UserDatabaseDao

class UserRepositoryDb(private val userDao : UserDatabaseDao) {

    val readAllData : LiveData<List<UserDatabase>> = userDao.getAllUsers()

    suspend fun addUsers(user : UserDatabase) {
        userDao.insertUsers(user)
    }

}