package com.example.myapplication.ui.info

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.models.database.UserDatabase
import com.example.myapplication.models.response.User
import com.example.myapplication.room.dbrepository.UserRepositoryDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfoViewModel constructor(private var repositoryDb: UserRepositoryDb) : ViewModel() {

    var dbUser : LiveData<UserDatabase>? = null

    init {
        Log.d("Msg", "InfoViewModel is created!")
    }

    override fun onCleared() {
        Log.d("Msg", "InfoViewModel is cleared!")
        super.onCleared()
    }

    fun getUserFromName(userFullName : String) : LiveData<UserDatabase> {
        return repositoryDb.getUserFromName(userFullName)
    }

}
