package com.necatisozer.movievault.ui.main.movielist

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.necatisozer.movievault.R
import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.databinding.MovieListFragmentBinding
import com.necatisozer.movievault.ui.base.BaseFragment
import com.necatisozer.movievault.ui.base.OnItemClickListener
import com.necatisozer.movievault.ui.main.MainViewModel
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

class MovieListFragment : BaseFragment<MovieListViewModel, MovieListFragmentBinding>(),
    OnItemClickListener<Movie> {
    override val layoutRes = R.layout.movie_list_fragment
    override val viewModelClass = MovieListViewModel::class.java

    private lateinit var mainViewModel: MainViewModel

    private val moviesListAdapter: MovieListAdapter by lazy {
        MovieListAdapter().apply { clickListener = this@MovieListFragment }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(MainViewModel::class.java)
    }

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
                    val movie = moviesListAdapter.currentList.get(position)
                    overlayLayout.updateCurrentBackground(movie.posterUrl)
                    currentPosition = position
                }
            }
        }
    }

    private fun initializeViewModel() {
        viewModel.apply {
            movieListLiveData.observe(this@MovieListFragment, Observer {
                moviesListAdapter.submitList(it)
            })

            init()
        }
    }

    override fun onItemClick(data: Movie) {
        MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(data.id).also {
            view?.findNavController()?.navigate(it)
        }
    }
}
