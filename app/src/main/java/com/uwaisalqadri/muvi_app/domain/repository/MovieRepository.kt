package com.uwaisalqadri.muvi_app.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.uwaisalqadri.muvi_app.data.source.local.entity.MovieEntity
import com.uwaisalqadri.muvi_app.domain.model.Cast
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.model.Video
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

    fun searchMovies(
        apiKey: String,
        language: String,
        query: String,
        page: Int
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