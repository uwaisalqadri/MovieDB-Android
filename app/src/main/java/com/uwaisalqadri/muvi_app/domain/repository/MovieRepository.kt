package com.uwaisalqadri.muvi_app.domain.repository

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
}