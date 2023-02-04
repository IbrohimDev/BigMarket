package com.example.bigmarketapp.di.network

import android.content.Context
import androidx.paging.PagingConfig
import com.example.bigmarketapp.BuildConfig
import com.example.bigmarketapp.data.local.preferences.SharedPreferences
import com.example.bigmarketapp.data.remote.api.DeviceApi
import com.example.bigmarketapp.utils.addLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {
    @Provides
    @Singleton
    fun getSharedPref(context: Context):SharedPreferences  = SharedPreferences(context)

    @[Provides Singleton]
    fun booksApi(retrofit: Retrofit): DeviceApi =
        retrofit.create(DeviceApi::class.java)

    @[Provides Singleton]
    fun getConfig(): PagingConfig = PagingConfig(10)

    @[Provides Singleton]
    fun getOkHTTPClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addLoggingInterceptor(context)
            .build()

    @[Provides Singleton]
    fun getRetrofitNews(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


}