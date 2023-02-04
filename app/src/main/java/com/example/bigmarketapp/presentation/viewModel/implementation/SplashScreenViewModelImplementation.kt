package com.example.bigmarketapp.presentation.viewModel.implementation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.bigmarketapp.data.enum.Screens
import com.example.bigmarketapp.data.local.preferences.SharedPreferences
import com.example.bigmarketapp.domain.repository.PagerRepository
import com.example.bigmarketapp.presentation.viewModel.SplashScreenViewModel
import com.example.bigmarketapp.utils.isOnline
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImplementation @Inject constructor(
    private val repository: PagerRepository,
    private val context: Context,
    private val prefs:SharedPreferences
) : SplashScreenViewModel, ViewModel() {
    override val mainScreenNavigate =
        MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    override val throwToNoConnection =
        MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    init {
        prefs.screens = Screens.SPLASH
        if (isOnline(context)) {
            repository.getPagerImages()
        } else {
             throwToNoConnection.tryEmit(Unit)
        }
        repository.setSuccessLoadListener {
            mainScreenNavigate.tryEmit(Unit)
        }

    }
}