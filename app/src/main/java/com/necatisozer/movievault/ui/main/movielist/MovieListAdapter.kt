package com.necatisozer.movievault.ui.main.movielist

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.necatisozer.movievault.R
import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.databinding.MovieListItemBinding
import com.necatisozer.movievault.ui.base.BaseAdapter
import com.necatisozer.movievault.ui.base.BaseViewHolder
import com.necatisozer.movievault.utils.loadUrl

class MovieListAdapter :
    BaseAdapter<Movie, MovieListItemBinding, MovieListViewHolder>() {
    override val layoutRes = R.layout.movie_list_item
    override fun createViewHolder(binding: MovieListItemBinding) = MovieListViewHolder(binding)
}

class MovieListViewHolder(binding: MovieListItemBinding) :
    BaseViewHolder<Movie, MovieListItemBinding>(binding) {
    private val resources = binding.root.context.resources
    private val displayMetrics = resources.displayMetrics

    override fun bindData(data: Movie) {
        binding.root.updateLayoutParams<RecyclerView.LayoutParams> {
            when (resources.configuration.orientation) {
                ORIENTATION_PORTRAIT -> width = (displayMetrics.widthPixels * 0.7).toInt()
                ORIENTATION_LANDSCAPE -> height = (displayMetrics.heightPixels * 0.7).toInt()
            }
        }

        data.posterUrl?.let { binding.poster.loadUrl(it) }
    }
}