package com.example.picmars.models


import com.google.gson.annotations.SerializedName

data class Camera(
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    @SerializedName("rover_id")
    val roverId: Int
)