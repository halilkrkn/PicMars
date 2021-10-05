//package com.example.picmars.di
//
//import android.content.Context
//import androidx.room.Room
//import com.example.picmars.db.PicMarsPhotoDb
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.android.qualifiers.ApplicationContext
//import javax.inject.Singleton
//
//@Module
//object AppModule {
//
//    @Singleton
//    @Provides
//    fun provideRunningDatabase(
//
//        @ApplicationContext context: Context
//    ) = Room.databaseBuilder(
//        context,
//        PicMarsPhotoDb::class.java,
//        "picMarsPhotoDb.db"
//    ).build()
//
//
//    @Singleton
//    @Provides
//    fun providePicMarsPhotoDao(db:PicMarsPhotoDb) = db.getPicMarsPhotoDao()
//}