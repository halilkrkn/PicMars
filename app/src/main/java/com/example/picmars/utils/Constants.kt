package com.example.picmars.utils

import com.example.picmars.BuildConfig

class Constants {
    companion object {
        const val API_KEY = BuildConfig.PICMARS_API_KEY
        const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"
        const val PICMARS_STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 25
    }
}