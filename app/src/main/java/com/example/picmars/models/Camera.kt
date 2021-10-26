package com.example.picmars.models


import com.google.gson.annotations.SerializedName

data class Camera(
    var name: String,
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    @SerializedName("rover_id")
    val roverId: Int
)