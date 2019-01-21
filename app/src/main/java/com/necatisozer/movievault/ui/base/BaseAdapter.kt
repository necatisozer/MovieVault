package com.necatisozer.movievault.ui.base

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<D, H : BaseViewHolder<D, ViewDataBinding>> :
    ListAdapter<D, H>(DiffCallback<D>()) {
    var clickListener: OnItemClickListener<D>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: H, position: Int) {
        val data = currentList[position]
        holder.apply {
            bindData(data)
            itemView.setOnClickListener { clickListener?.onItemClick(data) }
        }
    }

    abstract fun onCreateViewHolder(root: ViewGroup): H
}

abstract class BaseViewHolder<D, out B : ViewDataBinding>(protected val binding: B) :
    RecyclerView.ViewHolder(binding.root) {
    protected val context: Context = binding.root.context
    abstract fun bindData(data: D)
}

class DiffCallback<D> : DiffUtil.ItemCallback<D>() {
    override fun areItemsTheSame(oldItem: D, newItem: D) = true
    override fun areContentsTheSame(oldItem: D, newItem: D) = oldItem == newItem
}

interface OnItemClickListener<D> {
    fun onItemClick(data: D)
}

