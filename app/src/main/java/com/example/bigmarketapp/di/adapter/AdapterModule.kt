package com.example.bigmarketapp.di.adapter

import android.content.Context
import com.example.bigmarketapp.presentation.ui.adapter.DevicesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class AdapterModule {

    @Provides
    fun pagingAdapter(context: Context) = DevicesAdapter(context)

}