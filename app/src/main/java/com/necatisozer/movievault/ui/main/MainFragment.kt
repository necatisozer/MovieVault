package com.necatisozer.movievault.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.necatisozer.movievault.R
import com.necatisozer.movievault.app.Injectable
import com.necatisozer.movievault.binding.FragmentDataBindingComponent
import com.necatisozer.movievault.databinding.MainFragmentBinding
import com.necatisozer.movievault.utils.PagerSnapHelper
import com.necatisozer.movievault.utils.RecyclerSnapItemListener
import com.necatisozer.movievault.utils.autoCleared
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var binding by autoCleared<MainFragmentBinding>()
    private lateinit var viewModel: MainViewModel
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        initializeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false,
            dataBindingComponent
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.movieListLiveData.observe(this, Observer { movieList ->
            moviesListAdapter.setItems(movieList)
        })

        viewModel.init()
    }

    private fun initializeView() {
        binding.setLifecycleOwner(viewLifecycleOwner)
        moviesListAdapter = MoviesListAdapter(requireActivity())
        binding.moviesList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.moviesList.adapter = moviesListAdapter

        val startSnapHelper = PagerSnapHelper(
            object : RecyclerSnapItemListener {
                override fun onItemSnap(position: Int) {
                    val movie = moviesListAdapter.getItem(position)
                    binding.overlayLayout.updateCurrentBackground(movie.posterUrl)
                }
            }
        )

        startSnapHelper.attachToRecyclerView(binding.moviesList)
    }
}
