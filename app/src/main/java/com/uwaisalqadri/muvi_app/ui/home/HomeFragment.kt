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
import com.uwaisalqadri.muvi_app.utils.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var toolbarBinding: IncludeToolbarBinding
    private lateinit var sliderIndicator: SliderIndicatorBinding

    private val sliderAdapter = GroupAdapter<GroupieViewHolder>()
    private val popularAdapter = GroupAdapter<GroupieViewHolder>()
    private val upComingAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = binding.toolbar
        sliderIndicator = binding.sliderIndicator

        with(viewModel) {
            getDiscoverMovies(1)
            messageData.observe(viewLifecycleOwner) {
                context?.showToast(it)
            }

            showProgressbar.observe(viewLifecycleOwner) {
                Timber.d("isLoading $it")
            }
        }

        toolbarBinding.apply {
            inputSearch.isVisible = false
            logo.isVisible = true
            btnSearch.setImageResource(R.drawable.ic_notification)
        }
    }
}