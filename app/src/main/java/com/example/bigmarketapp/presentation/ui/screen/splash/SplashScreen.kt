package com.example.bigmarketapp.presentation.ui.screen.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bigmarketapp.R
import com.example.bigmarketapp.presentation.ui.base.BaseFragment
import com.example.bigmarketapp.presentation.viewModel.SplashScreenViewModel
import com.example.bigmarketapp.presentation.viewModel.implementation.SplashScreenViewModelImplementation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashScreen : BaseFragment(R.layout.screen_splash), BaseSplash {

    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImplementation>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun initialize() {

    }

    override fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.mainScreenNavigate.collect {
                navController.navigate(R.id.mainScreen)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.throwToNoConnection.collect {
                navController.popBackStack()
                navController.navigate(R.id.noConnectionScreen)
            }
        }
    }

}