package com.necatisozer.movievault.ui.main.moviedetail

import android.view.ViewGroup
import com.necatisozer.movievault.R
import com.necatisozer.movievault.data.repository.entity.Cast
import com.necatisozer.movievault.databinding.CreditItemBinding
import com.necatisozer.movievault.extension.inflater
import com.necatisozer.movievault.extension.loadDrawableRes
import com.necatisozer.movievault.extension.loadUrlAsCircle
import com.necatisozer.movievault.ui.base.BaseAdapter
import com.necatisozer.movievault.ui.base.BaseViewHolder

class CastAdapter : BaseAdapter<Cast, CastViewHolder>() {
    override fun onCreateViewHolder(root: ViewGroup) =
        CastViewHolder(CreditItemBinding.inflate(root.inflater(), root, false))
}

class CastViewHolder(binding: CreditItemBinding) :
    BaseViewHolder<Cast, CreditItemBinding>(binding) {

    override fun bindData(data: Cast) {
        binding.apply {
            data.profilePath?.let { photo.loadUrlAsCircle(it) }
                ?: photo.loadDrawableRes(R.drawable.profile_placeholder)
            name.text = data.name
            character.text = data.character
        }
    }
}