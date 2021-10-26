package com.example.picmars.repository

import com.example.picmars.api.service.RetrofitInstance
import com.example.picmars.db.PicMarsPhotoDb
import com.example.picmars.models.Photo

class PicMarsRepository(
   private val db: PicMarsPhotoDb
){

    suspend fun getCuriosityRepo(sol: Int) =
        RetrofitInstance.api.getCuriosity(sol)

    suspend fun getOpportunityRepo(sol: Int) =
        RetrofitInstance.api.getOpportunity(sol)

    suspend fun getSpiritRepo(sol: Int) =
        RetrofitInstance.api.getSpirit(sol)

<<<<<<< Updated upstream
    suspend fun upsert(photos: Photo) = db.getPicMarsPhotoDao().upsert(photos)
=======
    suspend fun upsert(photos: List<Photo>) = db.getPicMarsPhotoDao().upsert(photos)



>>>>>>> Stashed changes

}