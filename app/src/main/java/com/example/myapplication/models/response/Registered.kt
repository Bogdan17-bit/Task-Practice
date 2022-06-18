package com.example.myapplication.models.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Registered (
    @SerializedName("date")
    val date : String,
    @SerializedName("age")
    val age : String
)