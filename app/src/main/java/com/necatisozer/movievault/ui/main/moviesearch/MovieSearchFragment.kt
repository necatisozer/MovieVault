package com.necatisozer.movievault.ui.main.moviesearch

import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.necatisozer.movievault.R
import com.necatisozer.movievault.databinding.MovieSearchFragmentBinding
import com.necatisozer.movievault.domain.entity.Movie
import com.necatisozer.movievault.ui.base.BaseFragment
import com.necatisozer.movievault.ui.base.OnItemClickListener
import com.necatisozer.movievault.ui.main.MainViewModel

class MovieSearchFragment : BaseFragment<MovieSearchViewModel, MovieSearchFragmentBinding>(),
    OnItemClickListener<Movie> {
    override val layoutRes = R.layout.movie_search_fragment
    override val viewModelClass = MovieSearchViewModel::class.java

    private lateinit var mainViewModel: MainViewModel
    private val movieSearchAdapter: MovieSearchAdapter by lazy {
        MovieSearchAdapter().apply { clickListener = this@MovieSearchFragment }
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
        binding.movieSearchList.apply {
            adapter = movieSearchAdapter
            layoutManager = when (context?.resources?.configuration?.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> GridLayoutManager(context, 2)
                else -> LinearLayoutManager(context)
            }
        }
    }

    private fun initializeViewModel() {
        viewModel.movieListLiveData.observe(this, Observer { movieSearchAdapter.submitList(it) })
        mainViewModel.apply {
            queryLiveData.observe(
                this@MovieSearchFragment,
                Observer { viewModel.onQueryTextChange(it) })
        }
    }

    override fun onItemClick(data: Movie) {
        mainViewModel.onShowMovieDetail()
        MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment(data.id).also {
            view?.findNavController()?.navigate(it)
        }
    }
}
