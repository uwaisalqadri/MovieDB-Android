package com.uwaisalqadri.muvi_app.data.repository

import com.uwaisalqadri.muvi_app.data.mapper.response.MovieResponseMapper
import com.uwaisalqadri.muvi_app.data.source.remote.ApiService
import com.uwaisalqadri.muvi_app.data.source.remote.response.MovieResponse
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    private val movieMapper = MovieResponseMapper()

    override fun getDiscoverMovies(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        page: Int,
        year: Int
    ): Single<List<Movie>> {
        return apiService.getDiscoverMovies(apiKey, language, sortBy, includeAdult, page, year).map {
            movieMapper.mapToListDomain(it.results)
        }
    }


}