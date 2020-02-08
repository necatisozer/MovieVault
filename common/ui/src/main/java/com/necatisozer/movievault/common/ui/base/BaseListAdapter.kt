package com.necatisozer.movievault.common.ui.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.necatisozer.movievault.common.ui.extension.bindingInflate

class BaseListAdapter<D : Any, B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<D> = defaultDiffCallback(),
    private val viewHolderBlock: BaseViewHolder<D, B>.() -> Unit
) : ListAdapter<D, BaseViewHolder<D, B>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<D, B> {
        val viewBinding: B = parent.bindingInflate(layoutId)
        return BaseViewHolder<D, B>(viewBinding).apply(viewHolderBlock)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<D, B>, position: Int) {
        holder.apply {
            data = currentList[position]
            bindingBlock?.invoke()
        }
    }

    companion object {
        private fun <D> defaultDiffCallback() = object : DiffUtil.ItemCallback<D>() {
            override fun areItemsTheSame(oldItem: D, newItem: D) = oldItem == newItem
            override fun areContentsTheSame(oldItem: D, newItem: D) = true
        }
    }
}