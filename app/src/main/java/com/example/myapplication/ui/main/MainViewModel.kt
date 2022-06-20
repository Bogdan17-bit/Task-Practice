package com.example.myapplication.ui.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.service.autofill.UserData
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.repository.UserRepository
import com.example.myapplication.models.database.UserDatabase
import org.koin.android.ext.android.inject
import com.example.myapplication.models.response.User
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.dbrepository.UserRepositoryDb
import com.example.myapplication.utils.UserConverter
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import org.koin.java.KoinJavaComponent.inject
import java.io.IOException
import java.net.URL
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class MainViewModel constructor(private var userRepository: UserRepository, private var repositoryDb: UserRepositoryDb) : ViewModel(){

    var job : Job? = null

    val usersList = MutableLiveData<List<User>>()
    val loading = MutableLiveData<Boolean>()
    val is_loaded = MutableLiveData<Boolean>(false)

    val dbPrepareUsersDb = mutableListOf<UserDatabase>()

    init {
        Log.d("Msg", "MainViewModel is created!")
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun getUsersDatabaseFromServerUsers(user : User) {
        dbPrepareUsersDb += UserConverter.getDatabaseUser(user)
        saveIntoDatabaseNewUsers()
    }

    fun saveIntoDatabaseNewUsers() {
        viewModelScope.launch (Dispatchers.IO) {
            repositoryDb.addUsers(dbPrepareUsersDb[0])
        }
    }

    fun getAllUsersFromDatabase() {
        viewModelScope.launch (Dispatchers.IO){
            var users = repositoryDb.readAllData
        }
    }

    fun getUsersFromServer() {
        job = CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            val response = userRepository.repoGetUsers(Random.nextInt(0, 100))
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    usersList.postValue(response.body()!!.users)
                    is_loaded.value = true
                    loading.value = false
                    Log.d("Msg", "Successful!")
                }
                else {
                    Log.d("Msg", "Error!")
                }
            }
        }
    }
}