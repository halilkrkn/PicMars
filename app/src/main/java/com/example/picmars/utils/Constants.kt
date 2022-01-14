package com.example.picmars.utils

import com.example.picmars.BuildConfig

class Constants {
    companion object{
        const val API_KEY = BuildConfig.PICMARS_API_KEY
        const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"
    }
}