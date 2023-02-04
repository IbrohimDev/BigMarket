package com.example.bigmarketapp.presentation.viewModel

import androidx.paging.PagingData
import com.example.bigmarketapp.data.remote.response.Offer
import com.example.bigmarketapp.domain.model.PagerImageData
import kotlinx.coroutines.flow.Flow

interface MainScreenViewModel {
    val pagerImagesValue: Flow<List<PagerImageData>>
    val nextImage: Flow<Unit>
    val deviceData: Flow<PagingData<Offer>>
    val devicesPagingInitialize:Flow<Unit>

    fun getDevices()
}