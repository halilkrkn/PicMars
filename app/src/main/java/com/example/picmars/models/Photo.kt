package com.example.picmars.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val PICMARS_ID = 0
@Entity(
    tableName = "photos"
)
data class Photo(
//    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded(prefix = "camera_")
    val camera: Camera,
    @SerializedName("earth_date")
    val earthDate: String,
    @SerializedName("img_src")
    val imgSrc: String,
    @Embedded(prefix = "rover_")
    val rover: Rover,
    val sol: Int
){
    @PrimaryKey(autoGenerate = true)
    var dbId: Int = PICMARS_ID
}