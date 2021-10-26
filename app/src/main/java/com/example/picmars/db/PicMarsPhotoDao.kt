package com.example.picmars.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.picmars.models.Camera
import com.example.picmars.models.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PicMarsPhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(photos: kotlin.collections.List<com.example.picmars.models.Photo>)

    @Query("SELECT * FROM photos")
    fun getAllPhotos(): LiveData<List<Photo>>

    @Query("SELECT * FROM photos WHERE camera_name LIKE '%' || :searchQuery || '%' ORDER BY camera_name DESC")
    fun searchCameraName(searchQuery: String): Flow<List<Photo>>

}