package com.uwaisalqadri.muvi_app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.FragmentHomeBinding
import com.uwaisalqadri.muvi_app.databinding.IncludeToolbarBinding
import com.uwaisalqadri.muvi_app.databinding.SliderIndicatorBinding
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.ui.detail.DetailActivity
import com.uwaisalqadri.muvi_app.utils.getCurrentDate
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
            getDiscoverMovies(2)
            getPopularMovies(1)
            getUpComingMovies(1, getCurrentDate("yyyy").toInt() + 1)
            discoverMovieData.observe(viewLifecycleOwner) { movies ->
                movies.slice(0 until 3).map {
                    sliderAdapter.add(SliderMovieItem(it) { item ->
                        startActivity(
                            Intent(context, DetailActivity::class.java)
                                .putExtra("movieId", item.id)
                        )
                    })
                }
            }
            popularMovieData.observe(viewLifecycleOwner) { movies ->
                movies.slice(0 until 10).map {
                    popularAdapter.add(MovieItem(it) { item ->
                        startActivity(
                            Intent(context, DetailActivity::class.java)
                                .putExtra("movieId", item.id)
                        )
                    })
                }
            }
            upComingMovieData.observe(viewLifecycleOwner) { movies ->
                movies.slice(0 until 10).map {
                    upComingAdapter.add(MovieItem(it) { item ->
                        startActivity(
                            Intent(context, DetailActivity::class.java)
                                .putExtra("movieId", item.id)
                        )
                    })
                }
            }
            messageData.observe(viewLifecycleOwner) {
                context?.showToast(it)
            }

            showProgressbar.observe(viewLifecycleOwner) {
                Timber.d("isLoading $it")
            }
        }

        with(binding.rvComingSoon) {
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = linearLayoutManager
            adapter = upComingAdapter
            setHasFixedSize(true)
        }

        with(binding.rvPopularMovies) {
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = linearLayoutManager
            adapter = popularAdapter
            setHasFixedSize(true)
        }

        with(binding.rvSlider) {
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = linearLayoutManager
            adapter = sliderAdapter
            setHasFixedSize(true)

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)

            // TODO = "implement auto scrolling"
//            val handler = Handler(Looper.getMainLooper())
//            handler.postDelayed({
//                scrollToPosition(sliderAdapter.getAda)
//            }, 5000)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val item = snapHelper.findSnapView(linearLayoutManager)
                        item?.let { linearLayoutManager.getPosition(it) }
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    when (linearLayoutManager.findFirstVisibleItemPosition()) {
                        0 -> {
                            sliderIndicator.apply {
                                item1.setImageResource(R.drawable.current_slider_item)
                                item2.setImageResource(R.drawable.uncurrent_slider_item)
                                item3.setImageResource(R.drawable.uncurrent_slider_item)
                            }
                        }
                        1 -> {
                            sliderIndicator.apply {
                                item1.setImageResource(R.drawable.uncurrent_slider_item)
                                item2.setImageResource(R.drawable.current_slider_item)
                                item3.setImageResource(R.drawable.uncurrent_slider_item)
                            }
                        }
                        2 -> {
                            sliderIndicator.apply {
                                item1.setImageResource(R.drawable.uncurrent_slider_item)
                                item2.setImageResource(R.drawable.uncurrent_slider_item)
                                item3.setImageResource(R.drawable.current_slider_item)
                            }
                        }
                    }
                }
            })

        }

        toolbarBinding.apply {
            inputSearch.isVisible = false
            logo.isVisible = true
            btnSearch.setImageResource(R.drawable.ic_notification)
        }
    }
}