package com.uwaisalqadri.muvi_app.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.ActivityDetailBinding
import com.uwaisalqadri.muvi_app.utils.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()

    private val castAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent?.extras?.get("movieId")
        Timber.d(intent.toString())

        with(viewModel) {
            val movieId = intent.toString()
            getDetailMovie(movieId)
            getDetailCredits(movieId)
            getDetailTrailer(movieId)

            detailMovieData.observe(this@DetailActivity) { movie ->
                binding.apply {
                    titleDetailMovie.text = movie.title
                    descriptionDetailMovie.text = movie.overview
                    movie.poster_path?.let { imgDetailMovie.loadImage(Constants.URL_IMAGE + it) }
                    playtimeDetailMovie.text = movie.release_date.formatDate()
                    movie.genres?.map { genresDetailMovie.text = it.name }
                }
            }

            castData.observe(this@DetailActivity) { casts ->
                casts.map {
                    castAdapter.add(CastItem(it))
                }
            }

            videoData.observe(this@DetailActivity) { videos ->
                videos.map { video ->
                    binding.btnWatchTrailer.setOnClickListener {
                        openLink(Constants.YOUTUBE_URL + video.key)
                    }
                }
            }

            messageData.observe(this@DetailActivity) {
                showToast(it)
            }
            showProgressBar.observe(this@DetailActivity) {
                //
            }
        }

        with(binding.rvDetailCast) {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}