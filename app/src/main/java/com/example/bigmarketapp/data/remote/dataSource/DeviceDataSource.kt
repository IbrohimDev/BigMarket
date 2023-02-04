package com.example.bigmarketapp.data.remote.dataSource

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bigmarketapp.data.remote.api.DeviceApi
import com.example.bigmarketapp.data.remote.response.Offer
import com.example.bigmarketapp.utils.isOnline
import timber.log.Timber
import javax.inject.Inject

class DeviceDataSource @Inject constructor(
    private val api: DeviceApi,
    private val context: Context
) : PagingSource<Int, Offer>() {
    override fun getRefreshKey(state: PagingState<Int, Offer>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        Timber.tag("HHH").d("Refresh key")
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Offer> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        Timber.tag("HHH").d("We start")


        val response = api.getDataByApi()

        return if (response.isSuccessful) {
            val article = response.body()!!.offers
            val nextKey = if (article.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(article, prevKey, nextKey)
        } else {
            LoadResult.Error(Throwable("Error with Connection"))
        }


    }
}