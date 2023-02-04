package com.example.bigmarketapp.domain.repository

import com.example.bigmarketapp.domain.model.PagerImageData

interface PagerRepository {
    val pagerDataList: List<PagerImageData>

    fun setSuccessLoadListener(block:() -> (Unit))

    fun getPagerImages()
}