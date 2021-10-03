package com.example.picmars.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.picmars.models.Photo

@Dao
interface PicMarsPhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(photos: Photo): Long

    @Query("SELECT * FROM photos")
    fun getAllPhotos(): LiveData<List<Photo>>

}