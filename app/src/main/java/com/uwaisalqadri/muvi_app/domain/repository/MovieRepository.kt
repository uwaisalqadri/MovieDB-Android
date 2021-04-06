package com.uwaisalqadri.muvi_app.domain.repository

import com.uwaisalqadri.muvi_app.data.source.remote.response.CastResponse
import com.uwaisalqadri.muvi_app.domain.model.Cast
import com.uwaisalqadri.muvi_app.domain.model.Movie
import io.reactivex.Single
import retrofit2.http.Path
import retrofit2.http.Query

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

    fun getDetailCredits(
        movieId: String,
        apiKey: String
    ) : Single<List<Cast>>
}