package com.uwaisalqadri.muvi_app.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}