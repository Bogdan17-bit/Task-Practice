package com.example.myapplication.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.database.UserDatabase
import com.example.myapplication.models.response.User
import com.example.myapplication.room.dbrepository.UserRepositoryDb
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class UserConverter {

    companion object {

        //var avatarBitmap : Bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)

        fun convertUserIntoDatabaseUserAndSave(user : User, userRepositoryDb: UserRepositoryDb) {
            val urlImage = URL(user.getPictureUrl())
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {
                val avatarBitmap = result.await()!!
                convert(avatarBitmap, user, userRepositoryDb)
            }
        }

        private fun convert(avatar: Bitmap, user : User, userRepositoryDb: UserRepositoryDb) {
            val dbUser = UserDatabase(
                0,
                user.getName(),
                user.getFullName(),
                user.getFullLocation(),
                user.getPhone(),
                user.getEmail(),
                user.getRegister(),
                avatar
            )
            GlobalScope.launch(Dispatchers.IO) {
                userRepositoryDb.addUser(dbUser)
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