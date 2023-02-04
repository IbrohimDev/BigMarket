package com.example.bigmarketapp.di.repository

import android.content.Context
import com.example.bigmarketapp.data.local.preferences.SharedPreferences
import com.example.bigmarketapp.domain.repository.PagerRepository
import com.example.bigmarketapp.domain.repository.implementation.PagerRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PagerRepositoryModule {

    @Binds
    @Singleton
    fun getAppRepository(impl: PagerRepositoryImplementation): PagerRepository



}