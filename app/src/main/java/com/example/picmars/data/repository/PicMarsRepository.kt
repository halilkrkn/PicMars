package com.example.picmars.data.repository

import com.example.picmars.data.api.service.PicMarsApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicMarsRepository @Inject constructor(
   private val picMarsApiService: PicMarsApiService
){

}