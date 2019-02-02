package com.necatisozer.movievault.ui.main.moviedetail

import android.view.ViewGroup
import com.necatisozer.movievault.R
import com.necatisozer.movievault.data.repository.entity.Crew
import com.necatisozer.movievault.databinding.CreditItemBinding
import com.necatisozer.movievault.extension.inflater
import com.necatisozer.movievault.extension.loadDrawableRes
import com.necatisozer.movievault.extension.loadUrlAsCircle
import com.necatisozer.movievault.ui.base.BaseAdapter
import com.necatisozer.movievault.ui.base.BaseViewHolder

class CrewAdapter : BaseAdapter<Crew, CrewViewHolder>() {
    override fun onCreateViewHolder(root: ViewGroup) =
        CrewViewHolder(CreditItemBinding.inflate(root.inflater(), root, false))
}

class CrewViewHolder(binding: CreditItemBinding) :
    BaseViewHolder<Crew, CreditItemBinding>(binding) {

    override fun bindData(data: Crew) {
        binding.apply {
            data.profilePath?.let { photo.loadUrlAsCircle(it) }
                ?: photo.loadDrawableRes(R.drawable.profile_placeholder)
            name.text = data.name
            character.text = data.job
        }
    }
}