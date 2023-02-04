package com.example.bigmarketapp.presentation.viewModel

import kotlinx.coroutines.flow.Flow

interface SplashScreenViewModel {

    val mainScreenNavigate: Flow<Unit>
    val throwToNoConnection: Flow<Unit>

}