package com.necatisozer.movievault.dynamicfeature.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.necatisozer.movievault.dynamicfeature.home.R

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
