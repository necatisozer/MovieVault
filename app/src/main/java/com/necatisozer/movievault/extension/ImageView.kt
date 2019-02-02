package com.necatisozer.movievault.extension

import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrlAsCircle(url: String) {
    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(this)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

fun ImageView.loadDrawableRes(@DrawableRes drawableRes: Int) {
    Glide.with(this).load(drawableRes).into(this)
}

fun ImageView.loadUrl(url: String, @DimenRes roundingRadius: Int) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions().transforms(
                CenterInside(),
                RoundedCorners(context.resources.getDimensionPixelSize(roundingRadius))
            )
        )
        .into(this)
}