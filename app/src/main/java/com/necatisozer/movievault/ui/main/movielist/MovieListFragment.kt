package com.necatisozer.movievault.ui.main.movielist

import android.os.Bundle
import androidx.lifecycle.Observer
import com.necatisozer.movievault.R
import com.necatisozer.movievault.databinding.MovieListFragmentBinding
import com.necatisozer.movievault.ui.base.BaseFragment
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

class MovieListFragment : BaseFragment<MovieListViewModel, MovieListFragmentBinding>() {
    override val layoutRes = R.layout.movie_list_fragment
    override val viewModelClass = MovieListViewModel::class.java

    private val moviesListAdapter: MoviesListAdapter by lazy { MoviesListAdapter(requireActivity()) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
        initializeViewModel()
    }

    private fun initializeView() {
        binding.apply {
            moviesList.apply {
                adapter = moviesListAdapter
                setItemTransitionTimeMillis(200)
                setItemTransformer(ScaleTransformer.Builder().setMinScale(0.8f).build())
                setSlideOnFling(true)

                var currentPosition: Int = -1
                addOnItemChangedListener { _, position ->
                    if (currentPosition == position) return@addOnItemChangedListener
                    val movie = moviesListAdapter.getItem(position)
                    overlayLayout.updateCurrentBackground(movie.posterUrl)
                    currentPosition = position
                }
            }
        }
    }

    private fun initializeViewModel() {
        viewModel.apply {
            movieListLiveData.observe(this@MovieListFragment, Observer { movieList ->
                moviesListAdapter.setItems(movieList)
            })

            init()
        }
    }
}
