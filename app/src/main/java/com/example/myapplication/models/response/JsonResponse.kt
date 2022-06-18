package com.example.myapplication.models.response


import com.google.gson.annotations.SerializedName

data class JsonResponse(
    @SerializedName("results")
    var users : List<User>
)
