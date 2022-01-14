package com.example.picmars.data.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Camera(
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    @SerializedName("rover_id")
    val roverId: Int
): Parcelable