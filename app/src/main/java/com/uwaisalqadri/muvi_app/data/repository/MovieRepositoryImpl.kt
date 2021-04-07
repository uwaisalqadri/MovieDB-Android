package com.uwaisalqadri.muvi_app.data.repository

import com.uwaisalqadri.muvi_app.data.mapper.entity.MovieEntityMapper
import com.uwaisalqadri.muvi_app.data.mapper.response.CastResponseMapper
import com.uwaisalqadri.muvi_app.data.mapper.response.MovieResponseMapper
import com.uwaisalqadri.muvi_app.data.mapper.response.VideoResponseMapper
import com.uwaisalqadri.muvi_app.data.source.local.MovieDao
import com.uwaisalqadri.muvi_app.data.source.remote.ApiService
import com.uwaisalqadri.muvi_app.data.source.remote.response.MovieItem
import com.uwaisalqadri.muvi_app.data.source.remote.response.MovieResponse
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
class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    private val movieMapper = MovieResponseMapper()
    private val movieEntityMapper = MovieEntityMapper()
    private val castMapper = CastResponseMapper()
    private val videoMapper = VideoResponseMapper()

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

    override fun getDetailMovie(
        movieId: String,
        apiKey: String,
        language: String
    ): Single<Movie> {
        return apiService.getDetailMovie(movieId, apiKey, language).map {
            movieMapper.mapToDomain(it)
        }
    }

    override fun getDetailCredits(movieId: String, apiKey: String): Single<List<Cast>> {
        return apiService.getDetailCredits(movieId, apiKey).map {
            castMapper.mapToListDomain(it.cast)
        }
    }

    override fun getDetailTrailer(movieId: String, apiKey: String): Single<List<Video>> {
        return apiService.getDetailTrailer(movieId, apiKey).map {
            videoMapper.mapToListDomain(it.results)
        }
    }

    override suspend fun insertMovie(movie: Movie) {
        val movieEntity = movieEntityMapper.mapToModel(movie)
        movieDao.insertMovie(movieEntity)
    }

    override suspend fun deleteMovie(movie: Movie) {
        val movieEntity = movieEntityMapper.mapToModel(movie)
        movieDao.deleteMovie(movieEntity)
    }

    override fun getMovieById(movieId: String): Single<List<Movie>> {
        return movieDao.getMovieById(movieId).map {
            movieEntityMapper.mapToListDomain(it)
        }
    }

    override fun getFavoriteMovies(): Single<List<Movie>> {
        return movieDao.getFavoriteMovies().map {
            movieEntityMapper.mapToListDomain(it)
        }
    }


}