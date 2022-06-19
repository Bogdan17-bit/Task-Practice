package com.example.myapplication.models.database

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.models.base.UserBaseManipulation


@Entity(tableName = "user")
data class UserDatabase(
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val name : String?,
    val fullName : String?,
    val fullLocation : String?,
    val phone : String?,
    val email : String?,
    val register : String?,
    val avatar : Bitmap?
)
