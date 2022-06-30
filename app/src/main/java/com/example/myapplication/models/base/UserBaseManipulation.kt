package com.example.myapplication.models.base

import android.widget.ImageView

interface UserBaseManipulation {

    fun getName() : String

    fun getFullName() : String

    fun getFullLocation() : String

    fun setImageBitmap(view: ImageView)

    fun getPhone() : String

    fun getEmail() : String

    fun getRegister() : String

}