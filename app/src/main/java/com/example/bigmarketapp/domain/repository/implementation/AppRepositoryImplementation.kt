package com.example.bigmarketapp.domain.repository.implementation

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.PagingData
import com.example.bigmarketapp.data.remote.api.DeviceApi
import com.example.bigmarketapp.data.remote.dataSource.DeviceDataSource
import com.example.bigmarketapp.data.remote.response.Offer
import com.example.bigmarketapp.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImplementation @Inject constructor(
    private val api: DeviceApi,
    private val config: PagingConfig,
    private val context: Context
) : AppRepository {

    override fun getDevices(scope: CoroutineScope): Flow<PagingData<Offer>> =
        Pager(config) {
            DeviceDataSource(api, context)
        }.flow.cachedIn(scope)

}