package com.example.picmars.repository

import com.example.picmars.api.service.RetrofitInstance
import com.example.picmars.db.PicMarsPhotoDb

class PicMarsRepository(
   private val db: PicMarsPhotoDb
){

    suspend fun getCuriosityRepo(sol: Int) =
        RetrofitInstance.api.getCuriosity(sol)

    suspend fun getOpportunityRepo(sol: Int) =
        RetrofitInstance.api.getOpportunity(sol)

    suspend fun getSpiritRepo(sol: Int) =
        RetrofitInstance.api.getSpirit(sol)
}