package com.example.bigmarketapp.data.remote.api

import com.example.bigmarketapp.data.remote.response.DeviceData
import com.example.bigmarketapp.data.remote.response.Offer
import retrofit2.Response
import retrofit2.http.GET

interface DeviceApi {

    @GET("/hh/test/api/v1/offers")
    suspend fun getDataByApi(): Response<DeviceData>
}