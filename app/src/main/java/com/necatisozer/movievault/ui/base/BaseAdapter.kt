package com.necatisozer.movievault.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<D, B : ViewDataBinding, H : BaseViewHolder<D, B>> :
    ListAdapter<D, H>(DiffCallback<D>()) {
    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), layoutRes, parent, false
        ) as B

        return createViewHolder(binding)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bindData(currentList[position])
    }

    abstract fun createViewHolder(binding: B): H
}

abstract class BaseViewHolder<D, B : ViewDataBinding>(protected val binding: B) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bindData(data: D)
}

class DiffCallback<D> : DiffUtil.ItemCallback<D>() {
    override fun areItemsTheSame(oldItem: D, newItem: D) = true
    override fun areContentsTheSame(oldItem: D, newItem: D) = oldItem == newItem
}

