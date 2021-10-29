package com.example.picmars.repository

import androidx.lifecycle.asLiveData
import com.example.picmars.api.service.RetrofitInstance
import com.example.picmars.db.PicMarsPhotoDao
import com.example.picmars.db.PicMarsPhotoDb
import com.example.picmars.models.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class PicMarsRepository(
   private val db: PicMarsPhotoDb
){

    suspend fun getCuriosityRepo(sol: Int) =
        RetrofitInstance.api.getCuriosity(sol)

    suspend fun getOpportunityRepo(sol: Int) =
        RetrofitInstance.api.getOpportunity(sol)

    suspend fun getSpiritRepo(sol: Int) =
        RetrofitInstance.api.getSpirit(sol)

    suspend fun upsert(photos: List<Photo>) = db.getPicMarsPhotoDao().upsert(photos)

    suspend fun search(searchPhotos: String) = db.getPicMarsPhotoDao().searchCameraName(searchPhotos)


}