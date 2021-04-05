package com.uwaisalqadri.muvi_app.ui.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.FragmentPopularBinding
import com.uwaisalqadri.muvi_app.databinding.IncludeToolbarBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@AndroidEntryPoint
class PopularFragment : Fragment(R.layout.fragment_popular) {

    private val binding: FragmentPopularBinding by viewBinding()
    private val viewModel: PopularViewModel by viewModels()

    private lateinit var toolbarBinding: IncludeToolbarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = binding.toolbar
    }
}