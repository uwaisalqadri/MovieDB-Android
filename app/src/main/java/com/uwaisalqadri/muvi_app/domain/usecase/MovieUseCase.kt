package com.uwaisalqadri.muvi_app.domain.usecase

import com.uwaisalqadri.muvi_app.data.source.local.entity.MovieEntity
import com.uwaisalqadri.muvi_app.domain.model.Cast
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.model.Video
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

    fun getDetailMovie(
        movieId: String,
        apiKey: String,
        language: String,
    ) : Single<Movie>

    fun getDetailCredits(
        movieId: String,
        apiKey: String
    ) : Single<List<Cast>>

    fun getDetailTrailer(
        movieId: String,
        apiKey: String
    ) : Single<List<Video>>

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    fun getMovieById(movieId: String): Single<List<Movie>>

    fun getFavoriteMovies(): Single<List<Movie>>
}














