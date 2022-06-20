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

        fun getDatabaseUser(user: User): UserDatabase {
            return UserDatabase(
                0,
                user.getShortName(),
                user.getFullName(),
                user.getFullLocation(),
                user.getPhone(),
                user.getEmail(),
                user.getRegisteredData(),
                setBitmap(user.getPictureUrl())
            )
        }

        private fun setBitmap(url: String): Bitmap? = runBlocking {
            val urlImage = URL(url)
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }
             result.await()
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