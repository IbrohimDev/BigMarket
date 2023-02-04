package com.example.bigmarketapp.presentation.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerAdapter<T : Any, V : ViewBinding, H : BaseRecyclerViewHolder<T, V>>(
    protected val diffCallback: DiffUtil.ItemCallback<T> = DefaultDiffCallback()
) : RecyclerView.Adapter<H>() {

    private class DefaultDiffCallback<T : Any>() : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }

    protected val differ = AsyncListDiffer(this, diffCallback)

    private var itemClickListener: ((T) -> Unit) = {}
    private var positionItemClickListener: ((Int, T) -> Unit) = { _, _ -> }

    fun setItemClickListener(listener: (T) -> Unit) {
        itemClickListener = listener
    }

    fun setPositionItemClickListener(listener: (Int, T) -> Unit) {
        positionItemClickListener = listener
    }

    fun submitList(list: List<T>) {
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(differ.currentList[position], itemClickListener, positionItemClickListener)
    }

    override fun getItemCount(): Int = differ.currentList.size
}

abstract class BaseRecyclerViewHolder<T, V : ViewBinding>(protected val binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(
        item: T,
        itemClickListener: (T) -> Unit,
        itemPositionClickListener: (Int, T) -> Unit
    )
}