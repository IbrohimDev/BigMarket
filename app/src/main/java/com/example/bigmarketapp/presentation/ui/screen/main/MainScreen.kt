package com.example.bigmarketapp.presentation.ui.screen.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bigmarketapp.R
import com.example.bigmarketapp.data.reciver.MyNetworkBroadCast
import com.example.bigmarketapp.data.remote.response.Offer
import com.example.bigmarketapp.databinding.ScreenMainBinding
import com.example.bigmarketapp.domain.model.PagerImageData
import com.example.bigmarketapp.presentation.ui.adapter.ContentsLoadStateAdapter
import com.example.bigmarketapp.presentation.ui.adapter.DevicesAdapter
import com.example.bigmarketapp.presentation.ui.base.BaseFragment
import com.example.bigmarketapp.presentation.viewModel.MainScreenViewModel
import com.example.bigmarketapp.presentation.viewModel.implementation.MainScreenViewModelImplementation
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import javax.inject.Inject

@AndroidEntryPoint
class MainScreen : BaseFragment(R.layout.screen_main), BaseMain {

    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImplementation>()
    private val binding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)

    @Inject
    lateinit var devicesAdapter: DevicesAdapter


    override fun initialize() {
        lifecycleScope.launchWhenStarted {
            viewModel.pagerImagesValue.collect {
                initializePagerImages(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.devicesPagingInitialize.collect {
                initializePagingAdapter()
            }
        }
    }

    override fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.nextImage.collect {
                observerNextImage()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.deviceData.collect {
                devicesAdapter.submitData(it)
            }
        }
    }

    override fun initializePagerImages(listImages: List<PagerImageData>) {
        binding.pager.registerLifecycle(lifecycle)
        val list = mutableListOf<CarouselItem>()
        listImages.forEach { item ->
            list.add(
                CarouselItem(
                    imageUrl = item.imageUrl
                )
            )
        }
        binding.pager.setData(list)
    }

    override fun observerNextImage() {
        binding.pager.next()
    }

    override fun initializePagingAdapter() {
        binding.rvCards.adapter = devicesAdapter
        binding.rvCards.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        devicesAdapter.withLoadStateHeaderAndFooter(
            ContentsLoadStateAdapter { devicesAdapter.retry() },
            ContentsLoadStateAdapter { devicesAdapter.retry() }
        )
    }


}