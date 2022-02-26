package com.example.picmars.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.picmars.data.api.service.PicMarsApiService
import com.example.picmars.data.paging.curiosity.CuriosityPagingSource
import com.example.picmars.data.paging.curiosity.CuriosityGetAllPagingSource
import com.example.picmars.data.paging.opportunity.OpportunityGetAllPagingSource
import com.example.picmars.data.paging.opportunity.OpportunityPagingSource
import com.example.picmars.data.paging.spirit.SpiritGetAllPagingSource
import com.example.picmars.data.paging.spirit.SpiritPagingSource
import com.example.picmars.utils.Constants.Companion.NETWORK_PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicMarsRepository @Inject constructor(
    private val picMarsApiService: PicMarsApiService
) {

    fun getCuriositySearchResult(queryCamera: String) =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CuriosityPagingSource(picMarsApiService, queryCamera)
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
                CuriosityGetAllPagingSource(picMarsApiService)
            }
        ).liveData


    fun getOpportunitySearchResult(queryCamera: String) =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                OpportunityPagingSource(picMarsApiService, queryCamera)
            }
        ).liveData


    fun allOpportunityPicMars() =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                OpportunityGetAllPagingSource(picMarsApiService)
            }
        ).liveData


    fun getSpiritSearchResult(queryCamera: String) =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SpiritPagingSource(picMarsApiService, queryCamera)
            }
        ).liveData


    fun allSpiritPicMars() =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SpiritGetAllPagingSource(picMarsApiService)
            }
        ).liveData
}

