package com.example.picmars.api.service

import com.example.picmars.util.Constants.Companion.API_KEY
import com.example.picmars.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.internal.notify
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

//    by lazy = atanan değer çalıştırılır.
////    lazy sadece val değişkenlerde kullanılır.
//    Lazy, değişken ilk defa çağrıldığında çalıştırılır ve ardından son satırdaki değeri, tanımlamak istediğimiz değişkene atar. Değişkene ikinci ve sonraki erişimlerde çalıştırılmaz, değişkene atamış olduğu değer döndürülür.

   companion object {
       private val retrofit by lazy {
           val logging = HttpLoggingInterceptor()
           logging.setLevel(HttpLoggingInterceptor.Level.BODY)

           val client = OkHttpClient.Builder()
               .addInterceptor(logging)
               .build()

           Retrofit.Builder()
               .client(client)
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .client(client)
               .build()
       }

       val api by lazy {
           retrofit.create(PicMarsApiService::class.java)
       }

    }

}

