package com.uwaisalqadri.muvi_app.data.source.remote

import com.uwaisalqadri.muvi_app.data.source.remote.response.CastResponse
import com.uwaisalqadri.muvi_app.data.source.remote.response.MovieItem
import com.uwaisalqadri.muvi_app.data.source.remote.response.MovieResponse
import com.uwaisalqadri.muvi_app.data.source.remote.response.VideoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
interface ApiService {

    @GET("discover/movie")
    fun getDiscoverMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int,
        @Query("year") year: Int
    ) : Single<MovieResponse>

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    )

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ) : Single<MovieItem>

    @GET("movie/{movie_id}/credits")
    fun getDetailCredits(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ) : Single<CastResponse>

    @GET("movie/{movie_id}/videos")
    fun getDetailTrailer(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ) : Single<VideoResponse>

}

















