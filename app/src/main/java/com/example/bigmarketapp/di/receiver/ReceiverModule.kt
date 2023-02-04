package com.example.bigmarketapp.di.receiver

import android.content.Context
import com.example.bigmarketapp.data.reciver.MyNetworkBroadCast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReceiverModule {

    @Provides
    @Singleton
    fun getReceiver(context: Context) = MyNetworkBroadCast(context)
}