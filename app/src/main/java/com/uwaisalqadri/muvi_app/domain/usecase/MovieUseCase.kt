package com.uwaisalqadri.muvi_app.domain.usecase

import com.uwaisalqadri.muvi_app.domain.model.Movie
import dagger.Provides
import io.reactivex.Single

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
interface MovieUseCase {

    fun getDiscoverMovies(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        page: Int,
        year: Int
    ) : Single<List<Movie>>
}