package com.uwaisalqadri.muvi_app.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.ActivityDetailBinding
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.utils.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()

    private val castAdapter = GroupAdapter<GroupieViewHolder>()

    private var isFavorite by Delegates.notNull<Boolean>()
    private lateinit var movieId: String
    private lateinit var movieData: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent?.extras?.get("movieId")
        movieId = intent.toString()
        Timber.d(intent.toString())

        binding.btnAddFavorite.setOnClickListener {
            when {
                !isFavorite -> viewModel.saveFavoriteMovie(movieData)
                isFavorite -> viewModel.removeFavoriteMovie(movieData)
            }
        }

        with(viewModel) {
            getDetailMovie(movieId)
            getDetailCredits(movieId)
            getDetailTrailer(movieId)

            detailMovieData.observe(this@DetailActivity) { movie ->
                binding.apply {
                    movieData = movie
                    titleDetailMovie.text = movieData.title
                    descriptionDetailMovie.text = movieData.overview
                    movieData.poster_path?.let { imgDetailMovie.loadImage(Constants.URL_IMAGE + it) }
                    playtimeDetailMovie.text = movieData.release_date.formatDate()
                    movieData.genres?.map { genresDetailMovie.text = it.name }
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

            favoriteState.observe(this@DetailActivity) { favoriteState ->
                when(favoriteState) {
                    is AddFavorite -> {
                        removeFavoriteView()
                        isFavorite
                    }
                    is RemoveFavorite -> {
                        addFavoriteView()
                        !isFavorite
                    }
                    is FavMovieDataFound -> {
                        favoriteState.movie.map {
                            if (it.id == movieId.toInt()) {
                                removeFavoriteView()
                                isFavorite
                            }
                        }
                    }
                }
            }
        }

        with(binding.rvDetailCast) {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun addFavoriteView() {
        binding.btnAddFavorite.apply {
            text = getString(R.string.add_favorite)
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add, 0, 0, 0)
        }
    }

    private fun removeFavoriteView() {
        binding.btnAddFavorite.apply {
            text = getString(R.string.remove_favorite)
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0)
        }
    }
}
















