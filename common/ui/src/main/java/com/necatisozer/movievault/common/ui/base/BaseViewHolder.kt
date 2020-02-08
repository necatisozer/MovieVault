package com.necatisozer.movievault.common.ui.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<D : Any, B : ViewDataBinding>(
    val viewBinding: B
) : RecyclerView.ViewHolder(viewBinding.root) {
    val context: Context get() = itemView.context

    lateinit var data: D
        internal set

    internal var bindingBlock: (() -> Unit)? = null

    fun bind(block: () -> Unit) {
        bindingBlock = block
    }
}