package com.necatisozer.movievault.ui.main.moviesearch

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.necatisozer.movievault.R
import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.databinding.MovieSearchItemBinding
import com.necatisozer.movievault.extension.gone
import com.necatisozer.movievault.extension.inflater
import com.necatisozer.movievault.extension.loadUrl
import com.necatisozer.movievault.ui.base.BaseAdapter
import com.necatisozer.movievault.ui.base.BaseViewHolder

class MovieSearchAdapter : BaseAdapter<Movie, MovieSearchViewHolder>() {
    override fun onCreateViewHolder(root: ViewGroup) =
        MovieSearchViewHolder(MovieSearchItemBinding.inflate(root.inflater(), root, false))
}

class MovieSearchViewHolder(binding: MovieSearchItemBinding) :
    BaseViewHolder<Movie, MovieSearchItemBinding>(binding) {
    init {
        val resources = binding.root.context.resources
        val displayMetrics = resources.displayMetrics

        binding.root.updateLayoutParams<RecyclerView.LayoutParams> {
            when (resources.configuration.orientation) {
                ORIENTATION_PORTRAIT -> height = (displayMetrics.widthPixels * 0.6).toInt()
                ORIENTATION_LANDSCAPE -> height = (displayMetrics.widthPixels * 0.3).toInt()
            }
        }
    }

    override fun bindData(data: Movie) {
        binding.apply {
            data.posterUrl?.let {
                binding.poster.loadUrl(
                    it,
                    R.dimen.movie_list_item_corner_radius
                )
            }

            title.text = context.getString(
                R.string.movie_title,
                data.title,
                data.releaseDate.year.toString()
            )

            if (data.originalTitle != data.title)
                originalTitle.text = data.originalTitle
            else {
                originalTitle.gone()
            }

            description.text = data.overview
        }
    }
}