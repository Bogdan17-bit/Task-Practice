package com.example.myapplication.models.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerializedName("country")
    val country : String,
    @SerializedName("state")
    val state : String
)
