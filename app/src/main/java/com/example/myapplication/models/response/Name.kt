package com.example.myapplication.models.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Name(
    @SerializedName("title")
    val title : String,
    @SerializedName("first")
    val first : String,
    @SerializedName("last")
    val last : String
)
