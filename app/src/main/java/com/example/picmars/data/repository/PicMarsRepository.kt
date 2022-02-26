package com.example.picmars.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.picmars.data.api.service.PicMarsApiService
import com.example.picmars.data.paging.curiosity.PicMarsCuriosityPagingSource
import com.example.picmars.data.paging.curiosity.PicMarsGetAllCuriosityPagingSource
import com.example.picmars.utils.Constants.Companion.NETWORK_PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicMarsRepository @Inject constructor(
   private val picMarsApiService: PicMarsApiService
){

   fun getPicMarsCuriositySearchResult(queryCamera: String) =
      Pager(
         config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            maxSize = 100,
            enablePlaceholders = false
         ),
         pagingSourceFactory = {
            PicMarsCuriosityPagingSource(picMarsApiService, queryCamera)
         }
      ).liveData


   fun allCuriosityPicMars() =
      Pager(
         config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            maxSize = 100,
            enablePlaceholders = false
         ),
         pagingSourceFactory = {
            PicMarsGetAllCuriosityPagingSource(picMarsApiService)
         }
      ).liveData
   }

