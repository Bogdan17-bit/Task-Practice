package com.example.myapplication.models.database

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.models.base.UserBaseManipulation


@Entity(tableName = "user")
data class UserDatabase(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    var name : String?,
    var fullName : String?,
    var fullLocation : String?,
    var phone : String?,
    var email : String?,
    var register : String?,
    var avatar : Bitmap?
)
