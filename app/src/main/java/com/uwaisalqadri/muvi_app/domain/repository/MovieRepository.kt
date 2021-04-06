package com.uwaisalqadri.muvi_app.domain.repository

import com.uwaisalqadri.muvi_app.domain.model.Cast
import com.uwaisalqadri.muvi_app.domain.model.Movie
import io.reactivex.Single

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
interface MovieRepository {

    fun getDiscoverMovies(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        page: Int,
        year: Int
    ) : Single<List<Movie>>

    fun getDetailMovie(
        movieId: String,
        apiKey: String,
        language: String,
    ) : Single<Movie>

    fun getDetailCredits(
        movieId: String,
        apiKey: String
    ) : Single<List<Cast>>
}