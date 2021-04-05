package com.uwaisalqadri.muvi_app.data.source.local.entity

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
data class MovieEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
