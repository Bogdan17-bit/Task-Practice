package com.example.myapplication.models.database

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.models.base.UserBaseManipulation


@Entity(tableName = "user")
data class UserDatabase(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    private val name : String?,
    private val fullName : String?,
    private val fullLocation : String?,
    private val phone : String?,
    private val email : String?,
    private val register : String?,
    private val avatar : Bitmap?
) : UserBaseManipulation {

    override fun getShortName(): String {
        return name!!
    }

    override fun getFullName(): String {
        return fullName!!
    }

    override fun getFullLocation(): String {
        return fullLocation!!
    }

    override fun setImageBitmap(view: ImageView) {
        view.setImageBitmap(avatar)
    }

    override fun getPhone(): String {
        return phone!!
    }

    override fun getEmail(): String {
        return email!!
    }

    override fun getRegisteredData(): String {
        return register!!
    }

}
