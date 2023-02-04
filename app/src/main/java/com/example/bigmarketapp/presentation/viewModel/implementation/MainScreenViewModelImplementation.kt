package com.example.bigmarketapp.presentation.viewModel.implementation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.bigmarketapp.data.enum.Screens
import com.example.bigmarketapp.data.local.preferences.SharedPreferences
import com.example.bigmarketapp.data.remote.response.Offer
import com.example.bigmarketapp.domain.model.PagerImageData
import com.example.bigmarketapp.domain.repository.AppRepository
import com.example.bigmarketapp.domain.repository.PagerRepository
import com.example.bigmarketapp.presentation.viewModel.MainScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImplementation @Inject constructor(
    private val repository: PagerRepository,
    private val appRepository: AppRepository,
    private val prefs: SharedPreferences
) : MainScreenViewModel, ViewModel() {

    override val pagerImagesValue =
        MutableSharedFlow<List<PagerImageData>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    override val nextImage =
        MutableSharedFlow<Unit>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val deviceData =
        MutableSharedFlow<PagingData<Offer>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    override val devicesPagingInitialize =
        MutableSharedFlow<Unit>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )


    init {
        prefs.screens = Screens.MAIN
        devicesPagingInitialize.tryEmit(Unit)
        viewModelScope.launch(Dispatchers.IO) {
            pagerImagesValue.emit(repository.pagerDataList)
            while (true) {
                delay(2000)
                nextImage.emit(Unit)
            }
        }
        getDevices()
    }

    override fun getDevices() {
        appRepository.getDevices(viewModelScope).onEach {
            deviceData.emit(it)
        }.launchIn(viewModelScope)
    }


}