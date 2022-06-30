package com.example.myapplication.room.dbrepository

import androidx.lifecycle.LiveData
import androidx.room.Database
import com.example.myapplication.models.database.UserDatabase
import com.example.myapplication.models.response.User
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.UserDatabaseDao

class UserRepositoryDb(private val userDao : UserDatabaseDao) {

    val readAllData : LiveData<List<UserDatabase>> = userDao.getAllUsers()

    suspend fun addUser(user : UserDatabase) {
        userDao.insertUser(user)
    }

    fun getUserFromName(name : String) : LiveData<UserDatabase> {
        return userDao.getUserWithFullName(name)
    }

}