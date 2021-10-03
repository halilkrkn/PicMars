package com.example.picmars.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.picmars.models.Photo

@Database(
    entities = [Photo::class],
    version = 1
)
abstract class PicMarsPhotoDb: RoomDatabase() {

    abstract fun getPicMarsPhotoDao(): PicMarsPhotoDao

    companion object{

        @Volatile
        private var instance: PicMarsPhotoDb ?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PicMarsPhotoDb::class.java,
                "article_db.db"
            ).build()
    }


}