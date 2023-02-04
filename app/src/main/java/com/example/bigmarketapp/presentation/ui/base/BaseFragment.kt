package com.example.bigmarketapp.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.example.bigmarketapp.presentation.ui.MainActivity


abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClicks()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
        initObservers()
        initClicks()
//        Base
    }

    override fun onDestroy() {
        super.onDestroy()
//        hideKeyboard()
    }

    protected fun NavDirections.navigate() {
//        navController.navigate(this)
    }

    protected abstract fun initialize()

    protected open fun initClicks() {}
    protected open fun initObservers() {}


    protected val mainActivity by lazy { requireActivity() as MainActivity }
}