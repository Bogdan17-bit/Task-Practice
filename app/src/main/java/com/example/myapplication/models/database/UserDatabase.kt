package com.example.myapplication.models.database

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.ui.base.BaseUser


@Entity(tableName = "user_table")
data class UserDatabase(
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    private var name : String?,
    private var fullName : String?,
    private var fullLocation : String?,
    private var phone : String?,
    private var email : String?,
    private var register : String?,
    var avatar : Bitmap?
) : BaseUser() {

    override fun getName(): String {
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

    override fun getRegister(): String {
        return register!!
    }
}
