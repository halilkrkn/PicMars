package com.example.picmars.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(
    tableName = "photos"
)
data class Photo(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @Embedded
    val camera: Camera,
    @SerializedName("earth_date")
    val earthDate: String,
    @SerializedName("img_src")
    val imgSrc: String,
    @Embedded
    val rover: Rover,
    val sol: Int


)