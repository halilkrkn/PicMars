package com.example.picmars.repository

import com.example.picmars.api.service.RetrofitInstance
import com.example.picmars.db.PicMarsPhotoDb

class PicMarsRepository(
   private val db: PicMarsPhotoDb
){

    suspend fun getCuriosityRepo(sol: Int, camera: String) =
        RetrofitInstance.api.getCuriosity(sol,camera)
}