package com.example.bigmarketapp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.bigmarketapp.R
import com.example.bigmarketapp.data.remote.response.Offer
import com.example.bigmarketapp.databinding.ItemCardBinding
import com.example.bigmarketapp.utils.visible
import javax.inject.Inject

class DevicesAdapter @Inject constructor(
    private val context: Context
) : PagingDataAdapter<Offer, DevicesAdapter.DevicesAdapterViewHolder>(DiffUtils) {


    object DiffUtils : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean =
            oldItem == newItem

    }

    inner class DevicesAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ItemCardBinding by viewBinding(ItemCardBinding::bind)

        init {


        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            Glide.with(context)
                .load(data!!.image.url).into(binding.roundedImageViewDevice)
            binding.textViewName.text = data.name
            binding.textViewBrand.text = data.brand


        }
    }

    override fun onBindViewHolder(holder: DevicesAdapterViewHolder, position: Int) {
        holder.onBind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesAdapterViewHolder =
        DevicesAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        )

}