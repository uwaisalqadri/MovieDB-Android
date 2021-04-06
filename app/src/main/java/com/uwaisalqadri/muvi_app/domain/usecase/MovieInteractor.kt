package com.uwaisalqadri.muvi_app.domain.usecase

import com.uwaisalqadri.muvi_app.domain.model.Cast
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.model.Video
import com.uwaisalqadri.muvi_app.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {

    override fun getDiscoverMovies(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        page: Int,
        year: Int
    ): Single<List<Movie>> {
        return movieRepository.getDiscoverMovies(apiKey, language, sortBy, includeAdult, page, year)
    }

    override fun getDetailMovie(movieId: String, apiKey: String, language: String): Single<Movie> {
        return movieRepository.getDetailMovie(movieId, apiKey, language)
    }

    override fun getDetailCredits(movieId: String, apiKey: String): Single<List<Cast>> {
        return movieRepository.getDetailCredits(movieId, apiKey)
    }

    override fun getDetailTrailer(movieId: String, apiKey: String): Single<List<Video>> {
        return movieRepository.getDetailTrailer(movieId, apiKey)
    }
}