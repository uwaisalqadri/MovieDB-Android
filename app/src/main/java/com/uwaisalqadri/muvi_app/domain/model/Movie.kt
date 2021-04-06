package com.uwaisalqadri.muvi_app.domain.model

import com.uwaisalqadri.muvi_app.data.source.remote.response.GenreItem

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
    val genres: List<GenreItem>?,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)