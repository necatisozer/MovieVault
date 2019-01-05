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
import com.necatisozer.movievault.R
import com.necatisozer.movievault.app.Injectable
import com.necatisozer.movievault.binding.FragmentDataBindingComponent
import com.necatisozer.movievault.databinding.MainFragmentBinding
import com.necatisozer.movievault.utils.autoCleared
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
        initializeViewModel()
    }

    private fun initializeView() {
        moviesListAdapter = MoviesListAdapter(requireActivity())

        binding.apply {
            setLifecycleOwner(viewLifecycleOwner)
            moviesList.apply {
                adapter = moviesListAdapter
                setItemTransitionTimeMillis(200)
                setItemTransformer(ScaleTransformer.Builder().setMinScale(0.8f).build())
                setSlideOnFling(true)

                var currentPosition: Int = -1
                addOnItemChangedListener { _, position ->
                    if (currentPosition == position) return@addOnItemChangedListener
                    val movie = moviesListAdapter.getItem(position)
                    binding.overlayLayout.updateCurrentBackground(movie.posterUrl)
                    currentPosition = position
                }
            }
        }
    }

    private fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java).apply {
                movieListLiveData.observe(this@MainFragment, Observer { movieList ->
                    moviesListAdapter.setItems(movieList)
                })

                init()
            }
    }
}
