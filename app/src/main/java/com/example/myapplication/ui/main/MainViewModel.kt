package com.example.myapplication.ui.main

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import kotlin.coroutines.coroutineContext

class MainViewModel constructor(private var userRepository: UserRepository) : ViewModel(){

    var job : Job? = null

    val usersList = MutableLiveData<List<User>>()
    val loading = MutableLiveData<Boolean>()
    val is_loaded = MutableLiveData<Boolean>(false)

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun getUsersFromServer() {
        job = CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            val response = userRepository.repoGetUsers(1)
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