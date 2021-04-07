package com.uwaisalqadri.muvi_app.ui.popular

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.FragmentPopularBinding
import com.uwaisalqadri.muvi_app.databinding.IncludeToolbarBinding
import com.uwaisalqadri.muvi_app.ui.detail.DetailActivity
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@AndroidEntryPoint
class PopularFragment : Fragment(R.layout.fragment_popular) {

    private val binding: FragmentPopularBinding by viewBinding()
    private val viewModel: PopularViewModel by viewModels()
    private val searchAdapter = GroupAdapter<GroupieViewHolder>()

    private lateinit var toolbarBinding: IncludeToolbarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = binding.toolbar

        var job: Job? = null
        toolbarBinding.apply {
            inputSearch.addTextChangedListener { editable ->
                job?.cancel()
                job = MainScope().launch {
                    delay(200L)
                    editable?.let {
                        if (editable.isNotEmpty()) {
                            viewModel.searchMovies(editable.toString())
                            binding.apply {
                                tvSearchedItem.text = "'${editable}'"
                                description.isVisible = true
                            }
                        } else {
                            searchAdapter.clear()
                            binding.apply {
                                tvSearchedItem.text = ""
                                description.isVisible = false
                            }
                        }
                    }
                }
            }

            btnSearch.setOnClickListener {
                val query = inputSearch.text.toString()
                viewModel.searchMovies(query)
            }
        }

        with(viewModel) {

            searchData.observe(viewLifecycleOwner) { movies ->
                movies.map { movie ->
                    searchAdapter.add(SearchItem(movie) {
                        startActivity(Intent(context, DetailActivity::class.java).putExtra("movieId", it.id))
                    })
                }
            }

            messageData.observe(viewLifecycleOwner) {
                context?.showToast(it)
            }

            showProgressBar.observe(viewLifecycleOwner) {
                // progressbar
            }
        }

        with(binding.rvSearchResult) {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(context, Constants.SPAN_COUNT, GridLayoutManager.VERTICAL, false)
        }
    }
}









