package com.necatisozer.movievault.ui.main.moviedetail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.necatisozer.movievault.R
import com.necatisozer.movievault.databinding.MovieDetailFragmentBinding
import com.necatisozer.movievault.domain.entity.Movie
import com.necatisozer.movievault.extension.gone
import com.necatisozer.movievault.extension.loadUrl
import com.necatisozer.movievault.extension.visible
import com.necatisozer.movievault.ui.base.BaseFragment

class MovieDetailFragment : BaseFragment<MovieDetailViewModel, MovieDetailFragmentBinding>() {
    override val layoutRes = R.layout.movie_detail_fragment
    override val viewModelClass = MovieDetailViewModel::class.java

    private val castAdapter: CastAdapter by lazy { CastAdapter() }
    private val crewAdapter: CrewAdapter by lazy { CrewAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
        initializeViewModel()
    }

    private fun initializeView() {
        binding.apply {
            castView.adapter = castAdapter
            crewView.adapter = crewAdapter
        }
    }

    private fun initializeViewModel() {
        val movieId = MovieDetailFragmentArgs.fromBundle(arguments!!).movieId
        viewModel.apply {
            movieListLiveData.observe(this@MovieDetailFragment, Observer { onLoadMovie(it) })
            castListLiveData.observe(
                this@MovieDetailFragment,
                Observer {
                    castAdapter.submitList(it.first)
                    crewAdapter.submitList(it.second)
                })
            init(movieId)
        }
    }

    private fun onLoadMovie(movie: Movie) {
        binding.apply {
            movie.backdropUrl?.let { poster.loadUrl(it) }

            title.text =
                getString(R.string.movie_title, movie.title, movie.releaseDate.year.toString())

            originalTitle.apply {
                if (movie.originalTitle != movie.title) {
                    text = movie.originalTitle
                    visible()
                } else {
                    gone()
                }
            }

            description.text = movie.overview
        }
    }
}

