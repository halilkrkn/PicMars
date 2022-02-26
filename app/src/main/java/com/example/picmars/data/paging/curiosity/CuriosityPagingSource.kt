package com.example.picmars.data.paging.curiosity

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.picmars.data.api.service.PicMarsApiService
import com.example.picmars.data.models.Photo
import com.example.picmars.utils.Constants.Companion.PICMARS_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CuriosityPagingSource @Inject constructor(
    private val picMarsApiService: PicMarsApiService,
    private val queryCamera: String
) : PagingSource<Int, Photo>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: PICMARS_STARTING_PAGE_INDEX
        return try {
            val response = picMarsApiService.getCuriosity(1000, queryCamera, position)
            val picMars = response.photos

            LoadResult.Page(
                data = picMars,
                prevKey = if (position == PICMARS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (picMars.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}