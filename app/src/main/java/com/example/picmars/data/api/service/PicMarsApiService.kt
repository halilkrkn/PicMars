package com.example.picmars.data.api.service

import com.example.picmars.data.api.PicMarsResponse
import com.example.picmars.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


//const val API_KEY = "9CiiI2QiFbnI4avshtw07UUMnxmsYgIXSFo6YUHl"
//const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"

interface PicMarsApiService {

    @Headers("Authorization:$API_KEY")
    @GET("rovers/curiosity/photos")
    suspend fun getCuriosity(
    @Query("sol") sol: Int,
    @Query("camera") camera: String,
    @Query("page") page: Int
    ): PicMarsResponse

    @Headers("Authorization:$API_KEY")
    @GET("rovers/opportunity/photos")
    suspend fun getOpportunity(
        @Query("sol") sol: Int,
        @Query("camera") camera: String,
        @Query("page") page: Int
    ): PicMarsResponse

    @Headers("Authorization:$API_KEY")
    @GET("rovers/spirit/photos")
    suspend fun getSpirit(
        @Query("sol") sol: Int,
        @Query("camera") camera: String,
        @Query("page") page: Int

    ): PicMarsResponse
}