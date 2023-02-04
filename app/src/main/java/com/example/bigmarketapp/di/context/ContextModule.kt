package com.example.bigmarketapp.di.context

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {

    @Provides
    @Singleton
    fun getContext(@ApplicationContext context: Context): Context = context
}