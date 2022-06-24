package com.example.myapplication.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.example.myapplication.models.database.UserDatabase
import com.example.myapplication.models.response.User
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class UserConverter {

    companion object {

        var avatarBitmap : Bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)

        fun getDatabaseUser(user: User): UserDatabase {
            getBitmap(user.getPictureUrl())
            return UserDatabase(
                0,
                user.getShortName(),
                user.getFullName(),
                user.getFullLocation(),
                user.getPhone(),
                user.getEmail(),
                user.getRegisteredData(),
                avatarBitmap
            )
        }

        fun getBitmap(url : String) {
            val urlImage = URL(url)
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {
                avatarBitmap = result.await()!!
            }
        }

        fun URL.toBitmap(): Bitmap? {
            return try {
                BitmapFactory.decodeStream(openStream())
            } catch (e: IOException) {
                null
            }
        }
    }
}