package com.uwaisalqadri.muvi_app.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.FragmentHomeBinding
import com.uwaisalqadri.muvi_app.databinding.IncludeToolbarBinding
import com.uwaisalqadri.muvi_app.databinding.SliderIndicatorBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var toolbarBinding: IncludeToolbarBinding
    private lateinit var sliderIndicator: SliderIndicatorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = binding.toolbar
        sliderIndicator = binding.sliderIndicator

        toolbarBinding.apply {
            inputSearch.isVisible = false
            logo.isVisible = true
            btnSearch.setImageResource(R.drawable.ic_notification)
        }
    }
}