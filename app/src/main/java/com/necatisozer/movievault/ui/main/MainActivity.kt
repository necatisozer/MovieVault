package com.necatisozer.movievault.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.necatisozer.movievault.R
import com.necatisozer.movievault.databinding.MainActivityBinding
import com.necatisozer.movievault.ui.base.BaseToolbarActivity

class MainActivity : BaseToolbarActivity<MainViewModel, MainActivityBinding>(),
    MenuItem.OnActionExpandListener, SearchView.OnQueryTextListener {
    override val layoutRes = R.layout.main_activity
    override val viewModelClass = MainViewModel::class.java
    override val toolbar by lazy { binding.toolbar.toolbar }
    override val navController: NavController by lazy { findNavController(R.id.container) }

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.setOnActionExpandListener(this)
        searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        navController.apply {
            popBackStack(R.id.movie_list_fragment, false)
            navigate(R.id.action_movie_list_fragment_to_movie_search_fragment)
        }
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        navController.run {
            if (currentDestination?.id == R.id.movie_search_fragment) {
                popBackStack()
            }
        }

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { viewModel.onQueryTextChange(it) }
        return true
    }

    private fun initViewModel() {
        viewModel.showMovieDetailLiveData.observe(
            this,
            Observer { invalidateOptionsMenu() })
    }
}
