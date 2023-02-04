package com.example.bigmarketapp.domain.repository

import androidx.paging.PagingData
import com.example.bigmarketapp.data.remote.response.Offer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getDevices(scope:CoroutineScope): Flow<PagingData<Offer>>
}