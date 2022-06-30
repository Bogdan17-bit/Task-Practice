package com.example.myapplication.models.response

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.example.myapplication.ui.base.BaseUser
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import java.io.IOException
import java.net.URL

@Serializable
data class User(
    @SerializedName("name")
    private val name : Name,
    @SerializedName("location")
    private val location : Location,
    @SerializedName("email")
    private val email : String,
    @SerializedName("registered")
    private val register : Registered,
    @SerializedName("phone")
    private val phone : String,
    @SerializedName("picture")
    private val picture : Picture,
) : BaseUser() {

    override fun getName(): String {
        return name.first
    }

    override fun getFullName(): String {
        return name.first + " " + name.title + " " + name.last
    }

    override fun getFullLocation(): String {
        return location.country + ", " + location.state
    }

    override fun setImageBitmap(view: ImageView) {
       val  url : URL = URL(picture.url)
        val result: Deferred<Bitmap?> = GlobalScope.async {
            url.toBitmap()
        }

        GlobalScope.launch(Dispatchers.Main) {
            // show bitmap on image view when available
            view.setImageBitmap(result.await())
        }
    }

    fun URL.toBitmap(): Bitmap?{
        return try {
            BitmapFactory.decodeStream(openStream())
        }catch (e: IOException){
            null
        }
    }

    override fun getPhone(): String {
        return phone
    }

    override fun getEmail(): String {
        return email
    }

    override fun getRegister(): String {
        return register.date + ", " + register.age
    }

    fun getPictureUrl(): String {
        return picture.url
    }
}

