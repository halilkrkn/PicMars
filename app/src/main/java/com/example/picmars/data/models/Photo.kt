package com.example.picmars.data.models


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "picMarsPhoto")
data class Photo(
    @PrimaryKey(autoGenerate = true)
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
): Parcelable
