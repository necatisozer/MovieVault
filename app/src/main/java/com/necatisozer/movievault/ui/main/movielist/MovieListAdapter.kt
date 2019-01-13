package com.necatisozer.movievault.ui.main.movielist

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.databinding.MovieListItemBinding
import com.necatisozer.movievault.extension.inflater
import com.necatisozer.movievault.extension.loadUrl
import com.necatisozer.movievault.ui.base.BaseAdapter
import com.necatisozer.movievault.ui.base.BaseViewHolder

class MovieListAdapter : BaseAdapter<Movie, MovieListViewHolder>() {
    override fun onCreateViewHolder(root: ViewGroup) =
        MovieListViewHolder(MovieListItemBinding.inflate(root.inflater(), root, false))
}

class MovieListViewHolder(binding: MovieListItemBinding) :
    BaseViewHolder<Movie, MovieListItemBinding>(binding) {
    init {
        val resources = binding.root.context.resources
        val displayMetrics = resources.displayMetrics

        binding.root.updateLayoutParams<RecyclerView.LayoutParams> {
            when (resources.configuration.orientation) {
                ORIENTATION_PORTRAIT -> width = (displayMetrics.widthPixels * 0.7).toInt()
                ORIENTATION_LANDSCAPE -> height = (displayMetrics.heightPixels * 0.7).toInt()
            }
        }
    }

    override fun bindData(data: Movie) {
        data.posterUrl?.let { binding.poster.loadUrl(it) }
    }
}