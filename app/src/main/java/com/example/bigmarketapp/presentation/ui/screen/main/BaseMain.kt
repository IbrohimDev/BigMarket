package com.example.bigmarketapp.presentation.ui.screen.main

import androidx.paging.PagingData
import com.example.bigmarketapp.data.remote.response.Offer
import com.example.bigmarketapp.domain.model.PagerImageData

interface BaseMain {

    fun initializePagerImages(list: List<PagerImageData>)

    fun observerNextImage()

    fun initializePagingAdapter()


}