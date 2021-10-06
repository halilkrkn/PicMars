package com.example.picmars.api.service

import com.example.picmars.models.PicMarsResponse
import com.example.picmars.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//const val API_KEY = "9CiiI2QiFbnI4avshtw07UUMnxmsYgIXSFo6YUHl"
//const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"

interface PicMarsApiService {

    @GET("rovers/curiosity/photos")
    suspend fun getCuriosity(
        @Query("sol")
        sol: Int = 1000,
        @Query("camera")
        camera: String,
//        @Query("page")
//        page: Int = 1,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<PicMarsResponse>

//    @GET("v1/rovers/opportunity/photos")
//    suspend fun getOpportunity(
//        @Query("sol")
//        sol: Int = 1000,
////        @Query("camera")
////        camera: String,
////        @Query("page")
////        page: Int = 1,
//        @Query("apiKey")
//        apiKey: String = API_KEY
//    ): Response<PicMarsResponse>
//
//    @GET("v1/rovers/spirit/photos")
//    suspend fun getSpirit(
//        @Query("sol")
//        sol: Int = 1000,
////        @Query("camera")
////        camera: String,
////        @Query("page")
////        page: Int = 1,
//        @Query("apiKey")
//        apiKey: String = API_KEY
//    ): Response<PicMarsResponse>

}