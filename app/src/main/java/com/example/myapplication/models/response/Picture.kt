package com.example.myapplication.models.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Picture(
    @SerializedName("large")
    val url : String
)
