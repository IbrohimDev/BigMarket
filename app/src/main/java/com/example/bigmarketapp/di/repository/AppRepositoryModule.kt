package com.example.bigmarketapp.di.repository

import com.example.bigmarketapp.domain.repository.AppRepository
import com.example.bigmarketapp.domain.repository.implementation.AppRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AppRepositoryModule {

    @Binds
    fun getAppRepository(impls: AppRepositoryImplementation): AppRepository

}