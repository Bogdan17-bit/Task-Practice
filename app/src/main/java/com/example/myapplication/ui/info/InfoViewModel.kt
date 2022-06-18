package com.example.myapplication.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.response.User

class InfoViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    var user = MutableLiveData<User>()

    fun setUserData(user : User) {
        this.user = MutableLiveData<User>(user)
    }


}