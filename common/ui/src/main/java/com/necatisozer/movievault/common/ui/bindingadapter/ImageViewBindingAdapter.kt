package com.necatisozer.movievault.common.ui.bindingadapter

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.api.load
import com.necatisozer.movievault.common.ui.R
import splitties.resources.color
import splitties.resources.drawable

@BindingAdapter("imageUrl", "imagePlaceholder", requireAll = false)
fun ImageView.imageUrl(url: String?, @DrawableRes placeholderId: Int?) {
    load(url) {
        crossfade(true)
        placeholder(placeholderId?.let {
            drawable(it)
        } ?: ColorDrawable(color(R.color.placeholder)))
    }
}